package com.api.countdawnapi.repository;

import com.api.countdawnapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUserNameIsAndPassword(String userName,String password);
}
