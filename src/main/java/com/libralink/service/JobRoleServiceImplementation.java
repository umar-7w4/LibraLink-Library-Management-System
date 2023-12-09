package com.libralink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libralink.entity.Inventory;
import com.libralink.entity.JobRole;
import com.libralink.exception.InventoryException;
import com.libralink.exception.JobRoleException;
import com.libralink.repository.JobRoleRepository;

@Service("JobRoleService")
public class JobRoleServiceImplementation implements JobRoleService {
    
    @Autowired
    private JobRoleRepository jobRoleRepository;

    @Override
    public JobRole addJobRole(JobRole jobRole) throws JobRoleException {
        try {
            return jobRoleRepository.saveAndFlush(jobRole);
        } catch (Exception e) {
            throw new JobRoleException("Failed to add job role: " + e.getMessage());
        }
    }

    @Override
    public JobRole removeJobRole(int jobRoleId) throws JobRoleException {
        return jobRoleRepository.findById(jobRoleId)
            .map(jobRole -> {
                jobRoleRepository.deleteById(jobRoleId);
                return jobRole;
            })
            .orElseThrow(() -> new JobRoleException("Job Role with ID " + jobRoleId + " not found"));
    }

    @Override
    public JobRole updateJobRole(JobRole jobRole) throws JobRoleException {
        if (!jobRoleRepository.existsById(jobRole.getJobRoleId())) {
            throw new JobRoleException("Job Role with ID " + jobRole.getJobRoleId() + " not found");
        }
        return jobRoleRepository.saveAndFlush(jobRole);
    }

    @Override
    public JobRole getJobRole(int jobRoleId) throws JobRoleException {
        return jobRoleRepository.findById(jobRoleId)
            .orElseThrow(() -> new JobRoleException("Job Role with ID " + jobRoleId + " not found"));
    }

    @Override
    public List<JobRole> getAllJobRoles() throws JobRoleException {
        try {
            return jobRoleRepository.findAll();
        } catch (Exception e) {
            throw new JobRoleException("Failed to retrieve all job roles: " + e.getMessage());
        }
    }
}
