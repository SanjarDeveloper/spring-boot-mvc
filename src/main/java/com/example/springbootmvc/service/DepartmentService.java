package com.example.springbootmvc.service;

import com.example.springbootmvc.controller.CompanyController;
import com.example.springbootmvc.controller.DepartmentController;
import com.example.springbootmvc.dto.ApiResponse;
import com.example.springbootmvc.dto.DepartmentDTO;
import com.example.springbootmvc.entity.Company;
import com.example.springbootmvc.entity.Department;
import com.example.springbootmvc.repository.CompanyRepository;
import com.example.springbootmvc.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;


    public ApiResponse add(DepartmentDTO departmentDTO) {
        Optional<Company> optionalCompany = companyRepository.findById(departmentDTO.getCompanyId());
        if (optionalCompany.isEmpty()) return new ApiResponse("Akaajon bunaqa id yoq", false);
        Company company = optionalCompany.get();

        Department department = new Department();
        department.setName(departmentDTO.getName());
        department.setCompany(company);

        Department save = departmentRepository.save(department);
        return new ApiResponse("Saved", true, save);
    }

    public Department getInfo(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        Department department = optionalDepartment.get();
        DepartmentController.idd = department.getId();
        return department;
    }

    public ApiResponse saveUpdatedInfo(DepartmentDTO departmentDTO) {
        Optional<Company> optionalCompany = companyRepository.findById(departmentDTO.getCompanyId());
        if (optionalCompany.isEmpty()) return new ApiResponse("Bunaqa id yoq", false);
        Optional<Department> companyID = departmentRepository.findById(optionalCompany.get().getId());
//        departmentRepository.deleteById();
        //        departmentRepository.deleteById(companyID.get().getId());
        Company company = optionalCompany.get();
        Department department = new Department();
        department.setName(departmentDTO.getName());
        department.setCompany(company);
        Department save = departmentRepository.save(department);
        return new ApiResponse("Saved", true, save);
    }
}
