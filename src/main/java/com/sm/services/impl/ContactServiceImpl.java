package com.sm.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sm.entities.Contact;
import com.sm.entities.User;
import com.sm.helper.ResourceNotFoundException;
import com.sm.repositories.ContactRepo;
import com.sm.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepo contactRepo;
    @Override
    public void delete(String id) {
        var contact = contactRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Contact Not Found"));
        contactRepo.delete(contact);
        
    }

    @Override
    public List<Contact> getAll() {
        return contactRepo.findAll();
    }

    @Override
    public Contact getById(String id) {
        return contactRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Contact Not Found"));
    }

    @Override
    public List<Contact> getByUserId(String userId) {
        return contactRepo.findByUserId(userId);
    }

    @Override
    public Contact save(Contact contact) {
        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepo.save(contact);
    }

    @Override
    public List<Contact> search(String name, String email, String phoneNumber) {
        return null;
    }

    @Override
    public Contact update(Contact contact) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Contact> getByUser(User user) {
        return  contactRepo.findByUser(user);
    }
    

}
