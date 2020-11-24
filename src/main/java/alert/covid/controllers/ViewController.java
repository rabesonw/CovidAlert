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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;

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

    @GetMapping({"/error"})
    public String error() { return "error"; }

    @GetMapping({"/", "/index"})
    public String home() { return "index"; }

    @GetMapping({"/login"})
    public String login() { return "login"; }

    @GetMapping({"/changeUser"})
    public String changeUser() { return "changeUser"; }

    @GetMapping({"/listUsers"})
    @Secured("ROLE_ADMIN")
    public String listUsers() { return "listUsers"; }

    @PostMapping({"/doLogin"})
    public String doLogin(@ModelAttribute("user") User user) {
        return "login";
    }

    @GetMapping({"/register"})
    public String register() { return "register"; }

    @GetMapping({"/locations"})
    public String location() { return "locations"; }

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
