package com.makemytrip.makemytrip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.makemytrip.makemytrip.model.User;
import com.makemytrip.makemytrip.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User login(String email, String password){
        User user = userRepository.findByEmail(email);
        if(user!=null && passwordEncoder.matches(password,user.getPassword())){
            return user;
        }
        return null;
    }

    public User signup(User user){
        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new RuntimeException("email not registered");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRole()==null){
            user.setRole("USER");
        }
        return userRepository.save(user);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public User editProfile(String id, User updatedUser){
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            user.setFirstname(updatedUser.getFirstname());
            user.setLastname(updatedUser.getLastname());
            user.setPhoneNo(updatedUser.getPhoneNo());
            return userRepository.save(user);
        }
        return null;
    }
}
