package com.sm.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sm.entities.Contact;
import com.sm.entities.User;

import java.util.List;


@Repository
public interface ContactRepo extends JpaRepository<Contact,String>{
    Page<Contact> findByUser(User user, Pageable pageable);
    
    @Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
    List<Contact> findByUserId(String userId);
    Page<Contact> findByUserAndNameContaining(User user,String nameKeyword,Pageable pageable);
    Page<Contact> findByUserAndEmailContaining(User user,String emailKeyword,Pageable pageable);
    Page<Contact> findByUserAndPhoneNumberContaining(User user,String phoneKeyword,Pageable pageable);
}
