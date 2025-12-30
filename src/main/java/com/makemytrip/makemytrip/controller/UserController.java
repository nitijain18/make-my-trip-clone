package com.makemytrip.makemytrip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.makemytrip.makemytrip.model.User;
import com.makemytrip.makemytrip.service.UserService;




@RestController
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public User login(@RequestParam String email , @RequestParam String password){
        return userService.login(email, password);
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user){
        return ResponseEntity.ok(userService.signup(user));
    }
     
    @GetMapping("/get")
    public ResponseEntity<User> getuserbyemail(@RequestParam String email){
        User user = userService.getUserByEmail(email);
        if (user != null){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/edit")
    public User editprofile(@RequestParam String id,@RequestBody User updatedUser){
        return userService.editProfile(id, updatedUser);
    }
}
