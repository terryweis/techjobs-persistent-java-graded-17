package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Job extends AbstractEntity{

   @ManyToOne
   @NotNull(message = "Employer is required")
    private Employer employer;
    private String skills;


    public Job() {
    }

    // Initialize the id and value fields.
    public Job(Employer employer, String someSkills) {
        super();
        this.employer = employer;
        this.skills = someSkills;
    }

    // Getters and setters.


    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

}
