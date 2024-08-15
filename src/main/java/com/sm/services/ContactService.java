package com.sm.services;
import java.util.*;

import org.springframework.data.domain.Page;
import com.sm.entities.Contact;
import com.sm.entities.User;

public interface ContactService {
    Contact save(Contact contact);
    Contact update(Contact contact);
    List<Contact> getAll();
    Page<Contact> searchByName (String name,int size,int page,String sortBy,String order,User user);
    Page<Contact> searchByEmail(String email,int size,int page,String sortBy,String order,User user);
    Page<Contact> SerachbyPhoneNumber(String phoneNumberKeyword,int size,int page,String sortBy,String order,User user);
    Contact getById(String id);
    void delete(String id);
    List<Contact> getByUserId(String userId);

    Page<Contact> getByUser(User user,int page,int size,String sortField,String sortDirection);


}
