package com.libralink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libralink.entity.Checkout;
import com.libralink.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{


}
