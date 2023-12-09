package com.libralink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libralink.entity.Branch;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer>{

}
