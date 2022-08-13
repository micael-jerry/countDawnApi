package com.example.countdawnapi.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(nullable = false,length = 125)
    private String name;
    private String firstName;
    @Column(nullable = false,length = 100,name = "user_name")
    private String userName;
    @Column(nullable = false,length = 250)
    private String password;
    @Column(nullable = false)
    private Boolean admin;
}
