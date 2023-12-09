package com.libralink.controller;

import java.util.Collections;
import java.util.List;

import com.libralink.entity.Employee;
import com.libralink.entity.Member;
import com.libralink.exception.EmployeeException;
import com.libralink.exception.MemberException;

import java.util.List;

import com.libralink.entity.Checkout;
import com.libralink.exception.CheckoutException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libralink.entity.BookCopy;
import com.libralink.entity.Branch;
import com.libralink.exception.BookCopyException;
import com.libralink.exception.BookException;
import com.libralink.exception.BranchException;
import com.libralink.service.BookCopyService;
import com.libralink.service.BranchService;
import com.libralink.service.CheckoutService;
import com.libralink.service.EmployeeService;

@RestController
@RequestMapping("/libralink-employee")
public class EmployeeController {
    
    @Autowired
    EmployeeService employeeService;
    
    @PostMapping("/add")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) throws EmployeeException{
    	Employee newEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }


    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<Void> removeEmployee(@PathVariable int employeeId) throws EmployeeException {
        employeeService.removeEmployee(employeeId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) throws EmployeeException {
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @GetMapping("/get/{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int employeeId) throws EmployeeException {
        Employee employee = employeeService.getEmployee(employeeId);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Employee>> getAllEmployees() throws EmployeeException {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }
    
    
    @GetMapping("/employees/count")
    public ResponseEntity<?> getEmployeesCount() throws EmployeeException {
        long count = employeeService.countAllEmployees();
        return ResponseEntity.ok(Collections.singletonMap("count", count));
    }
    
}
