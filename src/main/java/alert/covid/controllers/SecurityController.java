package alert.covid.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
public class SecurityController {

    @RequestMapping(value ="/username", method= RequestMethod.GET)
    @ResponseBody
    public String connectedUsername(Principal principal) {
        return principal.getName();
    }
}
