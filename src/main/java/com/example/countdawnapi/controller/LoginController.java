package com.example.countdawnapi.controller;

import com.example.countdawnapi.Service.LoginService;
import com.example.countdawnapi.model.Login;
import com.example.countdawnapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/login")
public class LoginController {
    private LoginService loginService;

    @PostMapping(value = "")
    public Map<String, Boolean> log(@RequestBody Login log) {
        return loginService.postLog(log);
    }
}
