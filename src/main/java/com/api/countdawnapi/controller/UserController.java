package com.api.countdawnapi.controller;

import com.api.countdawnapi.repository.UserRepository;
import com.api.countdawnapi.Service.LoginService;
import com.api.countdawnapi.Service.UserService;
import com.api.countdawnapi.model.Login;
import com.api.countdawnapi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping(value = "")
    public User createUser(@RequestBody User user){
        userRepository.save(user);
        return user;
    }

    @GetMapping(value = "")
    public List<User> allUsers(){
        return userRepository.findAll();
    }

    @PostMapping(value = "/log")
    public Map<String,Boolean> log(@RequestBody Login log){
        return LoginService.login(userRepository.findUserByUserNameIsAndPassword(log.getUserName(),log.getPassword()),log);
    }

    @PutMapping(value = "/update/{id}")
    public User updateUser(@PathVariable int id,@RequestBody User user){
        User newUser = UserService.update(userRepository.findById(id).get(),user);
        userRepository.save(newUser);
        return newUser;
    }

    @DeleteMapping(value = "/delete/{id}")
    public void removeUser(@PathVariable int id){
        userRepository.deleteById(id);
    }
}
