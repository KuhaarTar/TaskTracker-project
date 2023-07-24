package com.kuhar.tasktracker.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import com.kuhar.tasktracker.enums.Role;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @Email
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String gitHubRef;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
