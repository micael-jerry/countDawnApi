package com.example.helloworldapi.Service;

import com.example.helloworldapi.model.Login;
import com.example.helloworldapi.model.User;

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
