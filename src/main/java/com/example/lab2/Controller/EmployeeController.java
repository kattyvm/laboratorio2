package com.example.lab2.Controller;

import com.example.lab2.Entity.Departments;
import com.example.lab2.Entity.EmployeeEntity;
import com.example.lab2.Entity.Job;
import com.example.lab2.Repository.EmployeeRepository;
import com.example.lab2.Repository.JobRepository;
import com.example.lab2.Repository.departmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    @Autowired
    JobRepository jobRepository;
    @Autowired
    departmentsRepository departmentsRepository;

    @GetMapping("/list")
    public String listarEmployees(Model model) {
        List<EmployeeEntity> listaEmp = employeeRepository.findAll();
        model.addAttribute("lista", listaEmp);
        return "employee/listar";
    }

    @GetMapping("/create")
    public String crearEmp(Model model) {
        List<Job> listaJob = jobRepository.findAll();
        List<Departments> listaDep = departmentsRepository.findAll();
        List<EmployeeEntity> listaMan = employeeRepository.findAll();
        model.addAttribute("listaJob", listaJob);
        model.addAttribute("listaDep", listaDep);
        model.addAttribute("listaMan", listaMan);
        return "employee/crear";
    }

    @PostMapping("/save")
    public String guardarEmp(EmployeeEntity emp) {

        if (emp.getEmployeeid()==null) {
            List<EmployeeEntity> listaEmp = employeeRepository.findAll(Sort.by("employeeid").descending());
            EmployeeEntity emp_mayorId = listaEmp.get(0);
            String mayorId = emp_mayorId.getEmployeeid();

            String[] idSplit = mayorId.split("_");
            int mayoridNum = Integer.valueOf(idSplit[0]);
            String idNumstr=String.valueOf(mayoridNum+1);

            Optional<Departments> optdepartment= departmentsRepository.findById(emp.getDepartment_id());
            Departments dep=optdepartment.get();
            String dSN=dep.getDepartmentshortname();

            String idFinal= idNumstr +"_" +dSN;
            emp.setEmployeeid(idFinal);
        }

        employeeRepository.save(emp);
        return "redirect:/employee/list";
    }

    @GetMapping("/edit")
    public String editarEmp(@RequestParam("id") String id,
                            Model model){
        Optional<EmployeeEntity> opt = employeeRepository.findById(id);
        if (opt.isPresent()) {
            EmployeeEntity employee =opt.get();

            List<Job> listaJob = jobRepository.findAll();
            List<Departments> listaDep = departmentsRepository.findAll();
            List<EmployeeEntity> listaMan = employeeRepository.findAll();
            model.addAttribute("listaJob", listaJob);
            model.addAttribute("listaDep", listaDep);
            model.addAttribute("listaMan", listaMan);
            model.addAttribute("employee", employee);
            return "employee/editar";
        } else {
            return "redirect:/employee/list";
        }
    }



}
