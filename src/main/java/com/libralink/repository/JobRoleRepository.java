package com.libralink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libralink.entity.Inventory;
import com.libralink.entity.JobRole;

@Repository
public interface JobRoleRepository extends JpaRepository<JobRole, Integer>{

}
