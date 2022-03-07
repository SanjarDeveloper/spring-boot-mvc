package com.example.springbootmvc.service;

import com.example.springbootmvc.controller.CompanyController;
import com.example.springbootmvc.dto.ApiResponse;
import com.example.springbootmvc.dto.CompanyDTO;
import com.example.springbootmvc.entity.Company;
import com.example.springbootmvc.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;

    public ApiResponse add(Company company) {
        Company save = companyRepository.save(company);
        return new ApiResponse("Saved", true, save);
    }

    public Company getInfo(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.get();
    }
    public ApiResponse saveUpdatedInfo(Company company){
        companyRepository.deleteById(CompanyController.idd);
        Company save = companyRepository.save(company);
        return new ApiResponse("Saved", true,save);
    }
}
