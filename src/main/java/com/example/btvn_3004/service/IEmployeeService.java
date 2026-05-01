package com.example.btvn_3004.service;

import com.example.btvn_3004.model.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IEmployeeService {
    List<Employee> findAll();
    void saveEmployeeWithAvatar(Employee employee, MultipartFile file);
}
