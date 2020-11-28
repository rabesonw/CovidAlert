package alert.covid.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Class SecurityController to get the username of the connected user
 */

@RestController
public class SecurityController {

    /**
     * GET mapping to return the connected user
     * @param principal the connected User info
     * @return String of the username
     */
    @RequestMapping(value ="/username", method= RequestMethod.GET)
    @ResponseBody
    public String connectedUsername(Principal principal) {
        return principal.getName();
    }
}
