package com.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "User")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public User(String userfirstname, String userlastname, String email, String password, String location) {
      this.userfirstname = userfirstname;  
      this.userlastname = userlastname;
      this.email = email;
      this.password = password;
      this.location = location;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    private String userfirstname;
    @NotBlank
    private String userlastname;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String location;
    
    //joins
    @ManyToMany(fetch = FetchType.LAZY) // das ist der spannende ORM Teil: automatisches Mapping von M-N Beziehungen :-)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private AssignedClass assignedClass;


}



