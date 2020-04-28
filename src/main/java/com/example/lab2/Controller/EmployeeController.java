package com.example.lab2.Controller;

import com.example.lab2.Entity.EmployeeEntity;
import com.example.lab2.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Entity;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/list")
    public String listarEmployees(Model model) {
        List<EmployeeEntity> listaEmp = employeeRepository.findAll();
        model.addAttribute("lista", listaEmp);
        return "employee/listar";
    }

    @GetMapping("/edit")
    public String editarEmp(@RequestParam("id") String id,
                            Model model){
        Optional<EmployeeEntity> opt = employeeRepository.findById(id);
        if (opt.isPresent()) {
            EmployeeEntity employee =opt.get();

            List<Job> listaJob = jobRepository.findAll();
            List<Department> listaDep = departmentRepository.findAll();
            List<Employee> listaMan = employeeRepository.findAll();
            model.addAttribute("listaJob", listaJob);
            model.addAttribute("listaDep", listaDep);
            model.addAttribute("listaMan", listaMan);
            model.addAttribute("employee", employee);
            return "employee/editar";
        } else {
            return "redirect:/employee/list";
        }
    }


    @GetMapping("/create")
    public String crearEmp(Model model) {
        List<Job> listaJob = jobRepository.findAll();
        List<Department> listaDep = departmentRepository.findAll();
        List<Employee> listaMan = employeeRepository.findAll();
        model.addAttribute("listaJob", listaJob);
        model.addAttribute("listaDep", listaDep);
        model.addAttribute("listaMan", listaMan);
        return "employee/crear";
    }



}
