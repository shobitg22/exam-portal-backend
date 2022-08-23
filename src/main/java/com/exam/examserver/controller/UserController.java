package com.exam.examserver.controller;

import com.exam.examserver.entity.Role;
import com.exam.examserver.entity.User;
import com.exam.examserver.entity.UserRole;
import com.exam.examserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private  UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/")
    public User addNewUser(@RequestBody User user)
    {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return this.userService.createUser(user);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String userName){
        return this.userService.getUser(userName);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User updatedUser,@PathVariable("id") Long userId){
        return this.userService.updateUser(updatedUser,userId);
    }
}
