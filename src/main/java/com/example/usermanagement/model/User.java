package com.example.usermanagement.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String name;
    private int age;
    private String country;
    private String email;
    private String phone;
    private String notificationPreference = "email";
    private LocalDateTime registrationDate;

}
