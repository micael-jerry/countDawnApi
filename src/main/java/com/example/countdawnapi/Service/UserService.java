package com.example.countdawnapi.Service;

import com.example.countdawnapi.model.User;
import com.example.countdawnapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public User postUser(User user) {
        userRepository.save(user);
        return user;
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User putUser(int id, User user){
        User newUser = update(userRepository.findById(id).get(), user);
        userRepository.save(newUser);
        return newUser;
    }

    public void deleteUserById(int id){
        userRepository.deleteById(id);
    }

    public static User update(User oldUser, User user) {
        if (user.getName() != null) {
            oldUser.setName(user.getName());
        }
        if (user.getFirstName() != null) {
            oldUser.setFirstName(user.getFirstName());
        }
        if (user.getUserName() != null) {
            oldUser.setUserName(user.getUserName());
        }
        if (user.getPassword() != null) {
            oldUser.setPassword(user.getPassword());
        }
        if (user.getAdmin() != oldUser.getAdmin()) {
            oldUser.setAdmin(user.getAdmin());
        }
        return oldUser;
    }
}
