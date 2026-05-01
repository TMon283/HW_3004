package com.example.btvn_3004.controller;

import com.example.btvn_3004.model.Employee;
import com.example.btvn_3004.repository.DepartmentRepository;
import com.example.btvn_3004.service.IEmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping
public class EmployeeController {

    private final IEmployeeService employeeService;

    private final DepartmentRepository departmentRepository;

    public EmployeeController(IEmployeeService employeeService, DepartmentRepository departmentRepository) {
        this.employeeService = employeeService;
        this.departmentRepository = departmentRepository;
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentRepository.findAll());
        return "form";
    }

    @PostMapping("/add")
    public String addEmployee(@ModelAttribute("employee") Employee employee,
                              @RequestParam("file") MultipartFile file) {
        employeeService.saveEmployeeWithAvatar(employee, file);
        return "redirect:/emp-list";
    }

    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "emp-list";
    }
}

