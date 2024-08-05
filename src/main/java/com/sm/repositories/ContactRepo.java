package com.sm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sm.entities.Contact;
import com.sm.entities.User;

import java.util.List;


@Repository
public interface ContactRepo extends JpaRepository<Contact,String>{
    List<Contact> findByUser(User user);
    
    @Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
    List<Contact> findByUserId(String userId);
    
}
