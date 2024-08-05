package com.sm.services;
import java.util.*;
import com.sm.entities.Contact;
import com.sm.entities.User;

public interface ContactService {
    Contact save(Contact contact);
    Contact update(Contact contact);
    List<Contact> getAll();
    List<Contact> search (String name,String email,String phoneNumber);
    Contact getById(String id);
    void delete(String id);
    List<Contact> getByUserId(String userId);

    List<Contact> getByUser(User user);
}
