package com.example.helloworldapi.repository;

import com.example.helloworldapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUserNameIsAndPassword(String userName,String password);
}
