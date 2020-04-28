package com.example.lab2.Controller;


import com.example.lab2.Entity.Job;
import com.example.lab2.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/job")
public class JobController {

    @Autowired
    JobRepository jobRepository;

    @GetMapping(value = "/lista")
    public String listajob(Model model){
        List<Job> listaJob = jobRepository.findAll();
        model.addAttribute("lista", listaJob);
        return "job/lista";
    }

}


