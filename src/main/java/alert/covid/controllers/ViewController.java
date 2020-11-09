package alert.covid.controllers;

import alert.covid.enums.StateCovid;
import alert.covid.models.Authority;
import alert.covid.models.User;
import alert.covid.repositories.AuthorityRepository;
import alert.covid.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ViewController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @PostMapping({"/doRegister"})
    public String register(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if(userRepository.existsUserByUsername(user.getUsername())) {
            return "register.jsp?user=true";
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setEnabled(true);
            user.setState_user(StateCovid.OK);
            userRepository.saveAndFlush(user);
            Authority auth = new Authority();
            auth.setUsername(user.getUsername());
            authorityRepository.saveAndFlush(auth);
            return "login";
        }
    }
}
