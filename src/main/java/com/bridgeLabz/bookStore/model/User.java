package com.bridgeLabz.bookStore.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;
    @Column(name = "f_name")
    private String firstName;
    @Column(name = "l_name")
    private String lastName;
    @Column(name = "date_of_birth")
    private LocalDate dob;
    @Column(name = "reg_date")
    private LocalDate registeredDate;
    @Column(name = "upd_date")
    private LocalDate updatedDate;
    @Column(name = "pwd")
    private String password;
    @Column(name = "email")
    private String email;
    @Enumerated(value = EnumType.STRING)
    private Role role;

}

