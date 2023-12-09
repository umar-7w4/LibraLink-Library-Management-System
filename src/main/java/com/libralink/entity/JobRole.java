package com.libralink.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 
 {
    "jobRoleId": 1,
    "roleName": "Manager",
    "salary": 60000.0,
}

 */

@Entity
public class JobRole {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobRoleId;

    @NotBlank(message = "Role name is required")
    @Size(max = 255, message = "Role name must be less than or equal to 255 characters")
    private String roleName;

    @Positive(message = "Salary must be a positive value")
    private double salary;

    @JsonIgnore 
    @OneToMany(mappedBy = "jobRole", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Employee> employees;
	
	public JobRole() {
		super();
	}

	public JobRole(int jobRoleId, String roleName, double salary, List<Employee> employees) {
		super();
		this.jobRoleId = jobRoleId;
		this.roleName = roleName;
		this.salary = salary;
		this.employees = employees;
	}

	public int getJobRoleId() {
		return jobRoleId;
	}

	public void setJobRoleId(int jobRoleId) {
		this.jobRoleId = jobRoleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
