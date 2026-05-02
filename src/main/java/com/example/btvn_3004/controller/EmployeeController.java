package com.example.btvn_3004.controller;

import com.example.btvn_3004.model.Employee;
import com.example.btvn_3004.repository.DepartmentRepository;
import com.example.btvn_3004.service.IEmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/employees")
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
        return "redirect:/employees";
    }

    @GetMapping
    public String listEmployees(Model model,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                @RequestParam(defaultValue = "name") String sortField,
                                @RequestParam(defaultValue = "asc") String sortDir,
                                @RequestParam(required = false) String keyword) {

        Page<Employee> employeePage = employeeService.searchEmployees(keyword, page, size, sortField, sortDir);

        if (page < 0) page = 0;
        if (page >= employeePage.getTotalPages() && employeePage.getTotalPages() > 0) {
            page = employeePage.getTotalPages() - 1;
            employeePage = employeeService.searchEmployees(keyword, page, size, sortField, sortDir);
        }

        model.addAttribute("employees", employeePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", employeePage.getTotalPages());
        model.addAttribute("totalItems", employeePage.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("keyword", keyword);

        return "employees";
    }
}

