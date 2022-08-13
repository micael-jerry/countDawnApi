package com.example.countdawnapi.Service;

import com.example.countdawnapi.model.Login;
import com.example.countdawnapi.model.User;

import java.util.HashMap;
import java.util.Map;

public class LoginService {
    public static Map<String,Boolean> login(User user, Login log){
        Map<String,Boolean> re = new HashMap<>();
        re.put("log", (user.getPassword().equals(log.getPassword()) && user.getUserName().equals(log.getUserName())));
        re.put("admin",user.getAdmin());
        return re;
    }
}
