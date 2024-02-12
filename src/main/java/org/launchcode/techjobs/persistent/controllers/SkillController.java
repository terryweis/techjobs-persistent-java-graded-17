package org.launchcode.techjobs.persistent.controllers;

import jakarta.validation.Valid;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Controller
@RequestMapping("skills")
public class SkillController {

    @Autowired
        private SkillRepository skillRepository;

        //findAll, save, findById

        @GetMapping("index")
        public String index(Model model){
            model.addAttribute("skills", skillRepository.findAll());
            return "skills";
        }

        @GetMapping("add")
        public String displayAddEmployerForm(Model model) {
            model.addAttribute(new Skill());
            return "skills/add";
        }

        @PostMapping("add")
        public String processAddEmployerForm(@ModelAttribute @Valid Skill newSkill,
                                             Errors errors, Model model) {

            if (errors.hasErrors()) {
                return "employers/add";
            }
            skillRepository.save(newSkill);
            return "redirect:";
        }

        @GetMapping("view/{employerId}")
        public String displayViewSkill(Model model, @PathVariable int skillId) {

            Optional optEmployer = skillRepository.findById(String.valueOf(skillId));
            if (optEmployer.isPresent()) {
                Skill skill = (Skill) optEmployer.get();
                model.addAttribute("skill", skill);
                return "skills/view";
            } else {
                return "redirect:../";
            }

        }
}

