package com.example.helloworldapi.Service;

import com.example.helloworldapi.model.User;

public class UserService {
    public static User update(User oldUser,User user){
        if(user.getName() != null){
            oldUser.setName(user.getName());
        }
        if(user.getFirstName() != null){
            oldUser.setFirstName(user.getFirstName());
        }
        if(user.getUserName() != null){
            oldUser.setUserName(user.getUserName());
        }
        if(user.getPassword() != null){
            oldUser.setPassword(user.getPassword());
        }
        if(user.getAdmin() != oldUser.getAdmin()){
            oldUser.setAdmin(user.getAdmin());
        }
        return oldUser;
    }
}
