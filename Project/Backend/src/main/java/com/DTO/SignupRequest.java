package com.DTO;

import java.util.Set;

import com.model.AssignedClass;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String userfirstname;
    @Size(min = 3, max = 20)
    private String userlastname;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    private Set<String> role;
    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    @NotBlank
    private String location;
    private String assigneClass;
    public void setUserfirstname(String userfirstname) {
        this.userfirstname = userfirstname;
    }
    public void setUserlastname(String userlastname) {
        this.userlastname = userlastname;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsername() {
        return userfirstname;
    }

    public void setUsername(String username) {
        this.userfirstname = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserfirstname() {
        return userfirstname;
    }

    public String getUserlastname() {
        return userlastname;
    }
    public String getAssignedCLass() {
        return assigneClass;
    }
}
