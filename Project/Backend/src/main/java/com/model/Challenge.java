package com.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // generates all getters and setters
@Table(name = "Challange")
@AllArgsConstructor //generates an all args constructor
@NoArgsConstructor //Generates a no args constructor
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer challangeId;

    @NotBlank
    private String challangeTitle;

    private String challangeSet;

    private int challangeTime;

    private boolean challangeDisplay;

    // joins
    @OneToMany(mappedBy = "challengefk", cascade = CascadeType.ALL)
    private Set<Play> plays = new HashSet<>();

    public Challenge( String challangeTitle, String challangeSet, int challangeTime, boolean challangeDisplay) {
        this.challangeTitle = challangeTitle;
        this.challangeSet = challangeSet;
        this.challangeTime = challangeTime;
        this.challangeDisplay = challangeDisplay;
    }
}
