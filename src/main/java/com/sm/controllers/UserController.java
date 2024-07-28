package com.sm.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sm.helper.Helper;
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping(value = "/dashboard")
    public String userDashBoard() {
        System.out.println("user dashboard");
        return "user/dashboard";
    }
    
    @RequestMapping(value = "/profile")
    public String userProfile(Authentication authentication) {
        String name = Helper.getEmailOfLoggedInUser(authentication);
        System.out.println(name);
        System.out.println("user profile");
        return "user/profile";
    }
}
