package com.example.springbootmvc.service;

import com.example.springbootmvc.dto.ApiResponse;
import com.example.springbootmvc.dto.DepartmentDTO;
import com.example.springbootmvc.dto.EmployeeDTO;
import com.example.springbootmvc.entity.Company;
import com.example.springbootmvc.entity.Department;
import com.example.springbootmvc.entity.Employee;
import com.example.springbootmvc.repository.CompanyRepository;
import com.example.springbootmvc.repository.DepartmentRepository;
import com.example.springbootmvc.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public ApiResponse add(EmployeeDTO employeeDTO) {
        Optional<Department> optionalDepartment = departmentRepository.findById(employeeDTO.getDepartmentId());
        if (optionalDepartment.isPresent()) return new ApiResponse("bunaqa id yoq", false);
        Department department = optionalDepartment.get();

        Employee employee = new Employee();
        employee.setFullname(employeeDTO.getFullname());
        employee.setDepartment(department);
//        department.setName(departmentDTO.getName());
//        department.setCompany(company);
        Employee save = employeeRepository.save(employee);
        return new ApiResponse("Saved", true, save);
    }
}
