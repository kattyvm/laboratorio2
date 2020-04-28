package com.example.lab2.Controller;

import com.example.lab2.Entity.Departments;
import com.example.lab2.Entity.EmployeeEntity;
import com.example.lab2.Entity.Location;
import com.example.lab2.Repository.EmployeeRepository;
import com.example.lab2.Repository.departmentsRepository;
import com.example.lab2.Repository.locationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/departments")
public class departmentsController {
    @Autowired
    departmentsRepository departmentsRepository;
    @Autowired
    locationRepository locationRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/lista")
    public String lista(Model model){
        List<Departments> listaDepartments = departmentsRepository.findAll();
        List<Location> listaLocation = locationRepository.findAll();
        model.addAttribute("lista",listaDepartments);
        model.addAttribute("listaLocation",listaLocation );
        return "deparments/listaDepartments";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){

        List<EmployeeEntity> listaEmployee = employeeRepository.findAll();
        List<Location> listaLocation = locationRepository.findAll();
        model.addAttribute("listaEmpleados", listaEmployee);
        model.addAttribute("listaLocation", listaLocation);
        return "departments/newDepartments"; }

    @PostMapping("/guardar")
    public String guardar(Departments departments, RedirectAttributes attr){

        if(departments.getDepartment_id()==0){
            List<Departments>listaActual = departmentsRepository.findAll();
            departments.setDepartment_id((listaActual.size()*10)+10);
            attr.addFlashAttribute("mensaje","Departamento Creado Exitosamente");
        }
        else
        {
            attr.addFlashAttribute("mensaje","Departamento Actualizado Exitosamente");
        }
        departmentsRepository.save(departments);
        return "redirect:/departments/lista";
    }

    @GetMapping("/editar")
    public String editar(Model model, @RequestParam ("id") int id){
        Optional<Departments> dep = departmentsRepository.findById(id);
        if(dep.isPresent()) {
            Departments departments = dep.get();
            model.addAttribute("department",departments);
            return "departments/editarDepartments";
        }
        else
        {
            return "redirect:/departments/lista";
        }

    }




}
