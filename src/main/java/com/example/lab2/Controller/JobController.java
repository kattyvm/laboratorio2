package com.example.lab2.Controller;


import com.example.lab2.Entity.Job;
import com.example.lab2.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/nuevo")
    public String nuevopersona(){
        return "/job/formulario";
    }

    @PostMapping("/guardar")
    public String guardarpersona(Job job){
        jobRepository.save(job);
        return "redirect:/job/lista";
    }

    @GetMapping("/editar")
    public String editarpersona(@RequestParam("id") String id, Model model){
        Optional<Job> opt = jobRepository.findById(id);
        if (opt.isPresent()){
            Job job = opt.get();
            model.addAttribute("job",job);
            return "/job/editar";
        }else {
            return "redirect:/job/lista";
        }
    }

    @GetMapping("/borrar")
    public String borrarpersona(@RequestParam("id") String id){
        Optional<Job> opt = jobRepository.findById(id);
        if (opt.isPresent()) {
            jobRepository.deleteById(id);
        }
        return "redirect:/job/lista";
    }

}


