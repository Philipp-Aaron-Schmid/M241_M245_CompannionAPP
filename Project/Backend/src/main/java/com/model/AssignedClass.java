package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "assignedClass")
@NoArgsConstructor
@AllArgsConstructor
public class AssignedClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private int timer;
    @Temporal(TemporalType.DATE) // Ensure to import jakarta.persistence.TemporalType
    private Date startDate; // The new field
    @OneToMany(mappedBy = "assignedClass", cascade =  CascadeType.ALL)
    private List<User> users = new ArrayList<>();
    @OneToMany(mappedBy = "aClass", cascade = CascadeType.ALL)
    private List<ClassModule> modules = new ArrayList<>();


}
