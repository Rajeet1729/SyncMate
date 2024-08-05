package com.sm.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sm.entities.User;
import com.sm.helper.AppContants;
import com.sm.helper.ResourceNotFoundException;
import com.sm.repositories.UserRepo;
import com.sm.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;
    private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @Override
    public void deleteUser(String id) {
        User user2 = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
        userRepo.delete(user2);
    }

    @Override
    public List<User> getAllUsers() {

        return userRepo.findAll();
    }

    @Override
    public Optional<User> getUserById(String id) {

        return userRepo.findById(id);
    }

    @Override
    public boolean isUserExist(String userId) {
        User user2 = userRepo.findById(userId).orElse(null);
        return user2 != null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        return user != null ? true : false;
    }

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleList(List.of(AppContants.ROLE_USER));

        return userRepo.save(user);
    }

    @Override
    public Optional<User> updateuser(User user) {
        User user2 = userRepo.findById(user.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
        user2.setName(user.getName());
        user2.setPassword(user.getPassword());
        user2.setEmail(user.getEmail());
        user2.setAbout(user.getAbout());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerified(user.isPhoneVerified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());

        User save = userRepo.save(user2);
        return Optional.ofNullable(save);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(null);
    }

}
