package com.example.springbootmvc.controller;

import com.example.springbootmvc.dto.DepartmentDTO;
//import com.example.springbootmvc.dto.EmployeeDTO;
import com.example.springbootmvc.dto.EmployeeDTO;
import com.example.springbootmvc.repository.DepartmentRepository;
import com.example.springbootmvc.repository.EmployeeRepository;
//import com.example.springbootmvc.service.EmployeeService;
import com.example.springbootmvc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping
    public String getEmployeePage(Model model){
        model.addAttribute("list",employeeRepository.findAll());
        return "employee/employee";
    }
    @GetMapping("/add")
    public String getSaveEmp(Model model){
        model.addAttribute("departmentList",departmentRepository.findAll());
        return "employee/employee-add";
    }

    @PostMapping("/add")
    public String saveEmployee(Model model, @ModelAttribute EmployeeDTO dto) {
        employeeService.add(dto);
        return "redirect:/employee";
    }

    @GetMapping("/delete/{id}") //1 45 24 90
    public String delete(@PathVariable Integer id) {
        employeeRepository.deleteById(id);
        return "redirect:/employee";
    }
}
