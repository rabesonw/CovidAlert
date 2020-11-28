package alert.covid.controllers;

import alert.covid.enums.StateCovid;
import alert.covid.models.Authority;
import alert.covid.models.User;
import alert.covid.models.VerificationToken;
import alert.covid.repositories.AuthorityRepository;
import alert.covid.repositories.UserRepository;
import alert.covid.repositories.VerificationTokenRepository;
import alert.covid.util.OnCreateUserEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;

/**
 * Class View Controller to serve views to the client
 */

@Controller
public class ViewController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ApplicationEventPublisher eventPublisher ;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    /***
     * GET mapping for error page
     * @return String of error page to load
     */
    @GetMapping({"/error"})
    public String error() { return "error"; }

    /***
     * GET mapping for home page
     * @return String of home page to load
     */
    @GetMapping({"/", "/index"})
    public String home() { return "index"; }

    /***
     * GET mapping for login page
     * @return String of login page to load
     */
    @GetMapping({"/login"})
    public String login() { return "login"; }

    /***
     * GET mapping for user list page
     * Reserved for administrators
     * @return String of user list page to load
     */
    @GetMapping({"/listUsers"})
    @Secured("ROLE_ADMIN")
    public String listUsers() { return "listUsers"; }

    /***
     * POST mapping for logging in
     * @param user the User to log
     * @return String the page login to laod
     */
    @PostMapping({"/doLogin"})
    public String doLogin(@ModelAttribute("user") User user) {
        return "login";
    }

    /***
     * GET mapping for myAccount page
     * @return String of the page myAccount to laod
     */
    @RequestMapping(value = "/myAccount", method = RequestMethod.GET)
    public String myAccount() {
        return "my_account";
    }

    /***
     * GET mapping for registering
     * @return String of the register page to load
     */
    @GetMapping({"/register"})
    public String register() { return "register"; }

    /**
     * POST mapping to register a user
     * The user is first added to the Database as an un-verified user
     * Once registered the user is redirected to the login page so they can login
     * after verifying their account.
     * @param user the User to register
     * @param result yes
     * @return String of the login page to load
     */
    @PostMapping({"/doRegister"})
    public String register(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if(userRepository.existsUserByUsername(user.getUsername())) {
            return "register.jsp?user=true";
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setState_user(StateCovid.OK);
            userRepository.saveAndFlush(user);
            Authority auth = new Authority();
            auth.setUsername(user.getUsername());
            authorityRepository.saveAndFlush(auth);
            eventPublisher.publishEvent(new OnCreateUserEvent("/",user ) ) ;
            return "login";
        }
    }

    /***
     * GET mapping to validate a user account
     * Receives the token to validate the user
     * Modifies the corresponding user in the database to validate the user
     * @param token the token given to the user to validate
     * @return the page to load
     */
    @GetMapping ({"/userConfirm"})
    public String confirmUser(@RequestParam("token") String token) {
        VerificationToken verifToken = verificationTokenRepository.getOne(token);
        if(verifToken != null) {
            Date date = Calendar.getInstance().getTime();
            if(date.before(verifToken.getExpiryDate())) {
                verificationTokenRepository.delete(verifToken);
                User user = userRepository.findByUsername(verifToken.getUsername());
                user.setEnabled(true);
                userRepository.saveAndFlush(user);
                return "login.jsp?confirm=true";
            }else {
                verificationTokenRepository.delete(verifToken);
                return "register.jsp?expired=true";
            } }
        else {
            return "register.jsp?expired=false"; }
    }

}
