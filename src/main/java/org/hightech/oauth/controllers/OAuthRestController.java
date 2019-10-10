package org.hightech.oauth.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class OAuthRestController {

    /*
    * Controller Endpoint to retrieve connected user
    */
    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

}
