package com.example.lab2.Controller;

import com.example.lab2.Entity.Departments;
import com.example.lab2.Repository.departmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class departmentsController {
    @Autowired
    departmentsRepository departmentsRepository;

    @GetMapping("/lista")
    public String lista(Model model){
        List<Departments> listaDepartments = departmentsRepository.findAll();
        model.addAttribute("lista",listaDepartments);
        return "deparments/listaDepartments";
    }





}
