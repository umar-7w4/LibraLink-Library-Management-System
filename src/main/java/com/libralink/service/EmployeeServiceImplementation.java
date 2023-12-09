package com.libralink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libralink.entity.Checkout;
import com.libralink.entity.Employee;
import com.libralink.exception.BookException;
import com.libralink.exception.CheckoutException;
import com.libralink.exception.EmployeeException;
import com.libralink.repository.EmployeeRepository;
import com.libralink.repository.JobRoleRepository;

@Service("EmployeeService")
public class EmployeeServiceImplementation implements EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private JobRoleRepository jobRoleRepository;

    @Override
    public Employee addEmployee(Employee employee) throws EmployeeException {
        try {
            employee.setJobRole(jobRoleRepository.findById(employee.getJobRole().getJobRoleId())
                .orElseThrow(() -> new EmployeeException("JobRole not found")));

            return employeeRepository.saveAndFlush(employee);
        } catch (Exception e) {
            throw new EmployeeException("Failed to add employee: " + e.getMessage());
        }
    }

    @Override
    public Employee removeEmployee(int employeeId) throws EmployeeException {
        return employeeRepository.findById(employeeId)
            .map(employee -> {
                employeeRepository.deleteById(employeeId);
                return employee;
            })
            .orElseThrow(() -> new EmployeeException("Employee with ID " + employeeId + " not found"));
    }

    @Override
    public Employee updateEmployee(Employee employee) throws EmployeeException {
        if (!employeeRepository.existsById(employee.getEmployeeId())) {
            throw new EmployeeException("Employee with ID " + employee.getEmployeeId() + " not found");
        }
        try {
            employee.setJobRole(jobRoleRepository.findById(employee.getJobRole().getJobRoleId())
                .orElseThrow(() -> new EmployeeException("JobRole not found")));

            return employeeRepository.saveAndFlush(employee);
        } catch (Exception e) {
            throw new EmployeeException("Failed to update employee: " + e.getMessage());
        }
    }

    @Override
    public Employee getEmployee(int employeeId) throws EmployeeException {
        return employeeRepository.findById(employeeId)
            .orElseThrow(() -> new EmployeeException("Employee with ID " + employeeId + " not found"));
    }

    @Override
    public List<Employee> getAllEmployees() throws EmployeeException {
        try {
            return employeeRepository.findAll();
        } catch (Exception e) {
            throw new EmployeeException("Failed to retrieve all employees: " + e.getMessage());
        }
    }
    
    @Override
    public long countAllEmployees() throws EmployeeException {
        try {
            return employeeRepository.count();
        } catch (Exception e) {
            throw new EmployeeException("Failed to count all employees: " + e.getMessage());
        }
    }
}
