package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.launchcode.techjobs.persistent.models.dto.JobSkillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
private EmployerRepository employerRepository;

    @Autowired
private JobRepository jobRepository;

    @Autowired
private SkillRepository skillRepository;

    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("title", "MyJobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
	model.addAttribute("title", "Add Job");
        Iterable<Employer> employers = employerRepository.findAll();
        Iterable<Skill> skills = skillRepository.findAll();
        model.addAttribute("employers",employerRepository.findAll());
        model.addAttribute("skills",skillRepository.findAll());
        model.addAttribute(new Job());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {


        if (errors.hasErrors()) {
	    model.addAttribute("title", "Add Job");
            return "add";
        }
        else {
            Optional<Employer> selectEmployer = employerRepository.findById(employerId);
            if (selectEmployer.isPresent()) {
                Employer employer = selectEmployer.get();
                newJob.setEmployer(employer);
                model.addAttribute("employerId", employer.getId());
            }
                List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
                newJob.setSkills(skillObjs);
                model.addAttribute("skills", skillObjs);

                jobRepository.save(newJob);



        }

        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

            return "view";
    }

}
