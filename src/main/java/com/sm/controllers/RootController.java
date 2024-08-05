package com.sm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sm.entities.User;
import com.sm.helper.Helper;
import com.sm.services.UserService;

@ControllerAdvice
public class RootController {
    @Autowired
    private UserService userService;
    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {
        if(authentication==null){
            return;
        }
        System.out.println("Adding logged in user information to the model");
        String name = Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.getUserByEmail(name);

        System.out.println(user.getName());
        System.out.println(user.getEmail());
        model.addAttribute("loggedInUser", user);
    }
}
