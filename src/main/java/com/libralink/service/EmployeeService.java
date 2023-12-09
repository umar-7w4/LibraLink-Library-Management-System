package com.libralink.service;

import java.util.List;

import com.libralink.entity.Branch;
import com.libralink.entity.Employee;
import com.libralink.exception.BookException;
import com.libralink.exception.EmployeeException;

public interface EmployeeService {

	Employee addEmployee(Employee employee) throws EmployeeException;
	
	Employee removeEmployee(int employeeId) throws EmployeeException;
	
	Employee updateEmployee(Employee employee) throws EmployeeException;
	
	Employee getEmployee(int employeeId) throws EmployeeException;
	
	List<Employee> getAllEmployees() throws EmployeeException;
	
	long countAllEmployees() throws EmployeeException;
}
