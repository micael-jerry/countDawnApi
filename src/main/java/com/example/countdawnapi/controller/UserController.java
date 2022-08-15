package com.example.countdawnapi.controller;

import com.example.countdawnapi.repository.UserRepository;
import com.example.countdawnapi.Service.LoginService;
import com.example.countdawnapi.Service.UserService;
import com.example.countdawnapi.model.Login;
import com.example.countdawnapi.model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
public class UserController {
    private UserService userService;

    @PostMapping(value = "")
    public User createUser(@RequestBody User user) {
        return userService.postUser(user);
    }

    @GetMapping(value = "")
    public List<User> allUsers() {
        return userService.getAllUser();
    }

    @PutMapping(value = "/update/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        return userService.putUser(id, user);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void removeUser(@PathVariable int id) {
        userService.deleteUserById(id);
    }
}
