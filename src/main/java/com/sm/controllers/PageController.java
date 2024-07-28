package com.sm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sm.entities.User;
import com.sm.forms.UserForm;
import com.sm.helper.Message;
import com.sm.helper.MessageType;
import com.sm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
public class PageController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public String index(){
        return "redirect:/home";
    }
    @RequestMapping("/home")
    public String home(){
        System.out.println("homepageHandler");
        return "home";
    }

    @RequestMapping("/about")
    public String about() {
        System.out.println("about page");
        return "about";
    }
    @RequestMapping("/services")
    public String services(){
        System.out.println("this is service page");
        return "services";
    }
    @GetMapping("/contact")
    public String Contact() {
        System.out.println("contact page");
        return new String("contact");
    }
    @GetMapping("/login")
    public String Login() {
        System.out.println("login page");
        return "login";
    }
    @RequestMapping(value = { "/register" }, method = RequestMethod.GET)
    public String Register(Model model) {
        System.out.println("register page");
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }
    
    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult, HttpSession session) {
       System.out.println("do-register");
        System.out.println(userForm);
        if(rBindingResult.hasErrors()){
            return "register";
        }
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setProfilePic("https://images.app.goo.gl/d3YPRqeypJrw94G6A");
        user.setPhoneNumber(userForm.getPhoneNumber());
        userService.saveUser(user);
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

        session.setAttribute("message", message);

        System.out.println("save user");
        return "redirect:/register";
    }
    
    
    
}
