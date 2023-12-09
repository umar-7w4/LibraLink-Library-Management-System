package com.libralink.service;

import java.util.List;

import com.libralink.entity.Branch;
import com.libralink.exception.BookException;
import com.libralink.exception.BranchException;

public interface BranchService {
	
	Branch addBranch(Branch branch) throws BranchException;
	
	Branch removeBranch(int branchId) throws BranchException;
	
	Branch updateBranch(Branch branch) throws BranchException;
	
	Branch getBranch(int branchId) throws BranchException;
	
	List<Branch> getAllBranchs() throws BranchException;
	

}
