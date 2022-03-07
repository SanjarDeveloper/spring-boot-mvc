package com.example.springbootmvc.controller;

import com.example.springbootmvc.entity.Company;
import com.example.springbootmvc.repository.CompanyRepository;
import com.example.springbootmvc.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/company")
public class CompanyController {
public static Integer idd = null;
    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyRepository companyRepository;

    //zaproslarni tutib ishlatish
//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String getCompanyPage(Model model) {

        model.addAttribute("list", companyRepository.findAll());
        //listini yuborish
        return "company/company";
    }

    @GetMapping("/add")
//    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String getSavecompany(Model model) {
        model.addAttribute("list",companyRepository.findAll());
        return "company/company-add";
    }

    @PostMapping("/add")
    public String saveCompany(Model model, @ModelAttribute Company company) {
        companyService.add(company);
        return "redirect:/company";
    }
    @GetMapping("/delete/{id}") //1 45 24 90
    public String delete(@PathVariable Integer id) {
        companyRepository.deleteById(id);
        return "redirect:/company";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        idd = id;
        model.addAttribute("idd", idd);
        model.addAttribute("info",companyService.getInfo(id));
        return "company/company-edit";
    }
    @PostMapping("/update")
    public String saveUpdatedInfo(@ModelAttribute Company company){
        companyService.saveUpdatedInfo(company);
        return "redirect:/company";
    }
}
