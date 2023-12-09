package com.libralink.service;

import java.util.List;

import com.libralink.entity.Inventory;
import com.libralink.entity.JobRole;
import com.libralink.exception.BookException;
import com.libralink.exception.JobRoleException;

public interface JobRoleService {

	
	JobRole addJobRole(JobRole jobRole) throws JobRoleException;
	
	JobRole removeJobRole(int jobRoleId) throws JobRoleException;
	
	JobRole updateJobRole(JobRole jobRole) throws JobRoleException;
	
	JobRole getJobRole(int jobRoleId) throws JobRoleException;
	
	List<JobRole> getAllJobRoles() throws JobRoleException;
}
