package com.libralink.controller;

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

import com.libralink.entity.JobRole;
import com.libralink.exception.JobRoleException;
import com.libralink.service.JobRoleService;

@RestController
@RequestMapping("/libralink-jobrole")
public class JobRoleController {
    
    @Autowired
    JobRoleService jobRoleService;
    
    @PostMapping("/add")
    public ResponseEntity<JobRole> addJobRole(@RequestBody JobRole jobRole) throws JobRoleException {
        JobRole newJobRole = jobRoleService.addJobRole(jobRole);
        return new ResponseEntity<>(newJobRole, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{jobRoleId}")
    public ResponseEntity<Void> removeJobRole(@PathVariable int jobRoleId) throws JobRoleException {
        jobRoleService.removeJobRole(jobRoleId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<JobRole> updateJobRole(@RequestBody JobRole jobRole) throws JobRoleException {
        JobRole updatedJobRole = jobRoleService.updateJobRole(jobRole);
        return ResponseEntity.ok(updatedJobRole);
    }

    @GetMapping("/get/{jobRoleId}")
    public ResponseEntity<JobRole> getJobRole(@PathVariable int jobRoleId) throws JobRoleException {
        JobRole jobRole = jobRoleService.getJobRole(jobRoleId);
        return ResponseEntity.ok(jobRole);
    }

    @GetMapping("/list")
    public ResponseEntity<List<JobRole>> getAllJobRoles() throws JobRoleException {
        List<JobRole> jobRoles = jobRoleService.getAllJobRoles();
        return ResponseEntity.ok(jobRoles);
    }
}
