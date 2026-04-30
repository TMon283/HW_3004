package com.example.btvn_3004.repository;

import com.example.btvn_3004.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {}

