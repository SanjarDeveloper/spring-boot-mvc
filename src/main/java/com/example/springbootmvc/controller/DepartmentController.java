package com.example.springbootmvc.controller;

import com.example.springbootmvc.dto.DepartmentDTO;
import com.example.springbootmvc.entity.Company;
import com.example.springbootmvc.entity.Department;
import com.example.springbootmvc.repository.CompanyRepository;
import com.example.springbootmvc.repository.DepartmentRepository;
import com.example.springbootmvc.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/department")
public class DepartmentController {
    public static Integer idd = null;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;

    //zaproslarni tutib ishlatish
//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String getDepartmentPage(Model model) {

        model.addAttribute("list", departmentRepository.findAll());
        //listini yuborish
        return "department/department";
    }

    @GetMapping("/add")
//    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String getSavedepartment(Model model) {

        model.addAttribute("companyList", companyRepository.findAll());

        return "department/department-add";
    }

    @PostMapping("/add")
    public String saveDepartment(Model model, @ModelAttribute DepartmentDTO dto) {
        departmentService.add(dto);
        return "redirect:/department";
    }

    @GetMapping("/delete/{id}") //1 45 24 90
    public String delete(@PathVariable Integer id) {
        departmentRepository.deleteById(id);
        return "redirect:/department";
    }
//    @GetMapping("/edit/{id}") My method
//    public String edit(@PathVariable Integer id, Model model){
//        idd = id;
//        model.addAttribute("companyList",companyRepository.findAll());
//        model.addAttribute("info",departmentService.getInfo(id));
//        return "department/department-edit";
//    }

    
    @PostMapping("/update")
    public String saveUpdatedInfo(@ModelAttribute DepartmentDTO departmentDTO){
        departmentService.saveUpdatedInfo(departmentDTO);
        return "redirect:/department";
    }
}
