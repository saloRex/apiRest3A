package com.tress.app.init.loggin;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class FacebookController {

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }
}