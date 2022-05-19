package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "auth")
@Getter
@Setter
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

//    @OneToOne
//    @JoinColumn(name = "user_id")
//    @NotNull
//    @Column(name = "user_id")
//    private User user;
//
//    @NotNull
//    @Size(min = 6, max = 10)
//    @Column(name = "password")
//    private String password;
}
