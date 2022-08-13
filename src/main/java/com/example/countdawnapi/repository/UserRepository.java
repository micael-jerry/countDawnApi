package com.example.countdawnapi.repository;

import com.example.countdawnapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUserNameIsAndPassword(String userName,String password);
}
