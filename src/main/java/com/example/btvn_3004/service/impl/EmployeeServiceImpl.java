package com.example.btvn_3004.service.impl;

import com.example.btvn_3004.model.Employee;
import com.example.btvn_3004.repository.EmployeeRepository;
import com.example.btvn_3004.service.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployeeWithAvatar(Employee employee, MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                String uploadDir = "uploads/";
                String originalFilename = file.getOriginalFilename();
                String newFilename = System.currentTimeMillis() + "_" + originalFilename;
                Path path = Paths.get(uploadDir + newFilename);
                Files.createDirectories(path.getParent());
                file.transferTo(path.toFile());
                employee.setAvatar(newFilename);
            }
            employeeRepository.save(employee);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi lưu file: " + e.getMessage());
        }
    }
}

