package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {
@NotBlank
    String description;
@ManyToMany(mappedBy = "skills")
private final List<Job> jobs = new ArrayList<>();
public Skill(){}

    public String getDescription() {
        return description;
    }


    public List<Job> getJobs() {
        return jobs;
    }



    public void setDescription(String description) {
        this.description = description;
    }
}
