package com.company.WeGoDent.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter

public class UserDTO {

    @NotNull
    @Size(min = 4, max = 24)
    @NotBlank(message = "username is mandatory")
    private String username;

    private String firstName;

    private String lastName;


    @Email
    @NotNull
    @NotBlank(message = "email is mandatory")
    private String email;

    private List<GroupRoleDTO> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<GroupRoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<GroupRoleDTO> roles) {
        this.roles = roles;
    }
}
