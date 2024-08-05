package com.sm.controllers;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sm.entities.Contact;
import com.sm.entities.User;
import com.sm.forms.ContactForm;
import com.sm.helper.Helper;
import com.sm.helper.Message;
import com.sm.helper.MessageType;
import com.sm.services.ContactService;
import com.sm.services.ImageService;
import com.sm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")

public class ContactController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;
    @Autowired
    private UserService userService;
    @Autowired
    private ImageService imageService;

    @RequestMapping("/add")
    public String addContactView(Model model) {
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, Authentication authentication,
            BindingResult result, HttpSession httpSession) {
        if (result.hasErrors()) {
            httpSession.setAttribute("message", Message.builder()
                    .content("Please correct the errors")
                    .type(MessageType.red)
                    .build());
            return "user/add_contact";
        }
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);
        logger.info("file information:",contactForm.getContactImage().getOriginalFilename());
        String filename = UUID.randomUUID().toString();

        String fileURL = imageService.uploadImage(contactForm.getContactImage(),filename);
        Contact contact = new Contact();
        contact.setName(contactForm.getName());
        contact.setFavorite(contactForm.isFavorite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setPicture(fileURL);
        contact.setCloudinaryImagePublicId(filename);
        contact.setUser(user);
        contactService.save(contact);
        httpSession.setAttribute("message", Message.builder()
                .content("Contact Added Successfully")
                .type(MessageType.green)
                .build());
        return "redirect:/user/contacts/add";

    }
    @RequestMapping
    public String viewContacts(Model model,Authentication authentication){
        String username = Helper.getEmailOfLoggedInUser(authentication);

        User user = userService.getUserByEmail(username);
        List<Contact> contacts= contactService.getByUser(user);

        model.addAttribute("contacts", contacts);
        return "user/contacts";
    }

}
