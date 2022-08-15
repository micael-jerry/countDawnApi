package com.example.countdawnapi.Service;

import com.example.countdawnapi.model.Login;
import com.example.countdawnapi.model.User;
import com.example.countdawnapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class LoginService {
    private UserRepository userRepository;

    public Map<String, Boolean> postLog(Login log) {
        return login(userRepository.findUserByUserNameIsAndPassword(log.getUserName(), log.getPassword()), log);
    }

    public static Map<String, Boolean> login(User user, Login log) {
        Map<String, Boolean> re = new HashMap<>();
        re.put("log", (user.getPassword().equals(log.getPassword()) && user.getUserName().equals(log.getUserName())));
        re.put("admin", user.getAdmin());
        return re;
    }
}
