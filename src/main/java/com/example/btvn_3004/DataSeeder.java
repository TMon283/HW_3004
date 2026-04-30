package com.example.btvn_3004;

import com.example.btvn_3004.model.Department;
import com.example.btvn_3004.model.Employee;
import com.example.btvn_3004.repository.DepartmentRepository;
import com.example.btvn_3004.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class DataSeeder implements CommandLineRunner {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DataSeeder(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) {
        if (departmentRepository.count() == 0 && employeeRepository.count() == 0) {
            Department itDept = new Department();
            itDept.setName("IT");
            itDept.setLocation("Hà Nội");
            departmentRepository.save(itDept);

            Department hrDept = new Department();
            hrDept.setName("HR");
            hrDept.setLocation("Hồ Chí Minh");
            departmentRepository.save(hrDept);

            Employee e1 = new Employee();
            e1.setName("Nguyễn Văn A");
            e1.setAge(28);
            e1.setAvatar("avatarA.jpg");
            e1.setStatus("ACTIVE");
            e1.setDepartment(itDept);

            Employee e2 = new Employee();
            e2.setName("Trần Thị B");
            e2.setAge(25);
            e2.setAvatar("avatarB.jpg");
            e2.setStatus("ACTIVE");
            e2.setDepartment(hrDept);

            employeeRepository.saveAll(Arrays.asList(e1, e2));
        }
    }
}

