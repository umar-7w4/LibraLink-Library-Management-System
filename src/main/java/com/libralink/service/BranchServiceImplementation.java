package com.libralink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.libralink.entity.Branch;
import com.libralink.exception.BranchException;
import com.libralink.repository.BranchRepository;

import java.util.List;
import java.util.Optional;

@Service("BranchService")
public class BranchServiceImplementation implements BranchService {
    
    @Autowired
    private BranchRepository branchRepository;

    @Override
    public Branch addBranch(Branch branch) throws BranchException {
        try {
            return branchRepository.saveAndFlush(branch);
        } catch (Exception e) {
            throw new BranchException("Failed to add branch: " + e.getMessage());
        }
    }

    @Override
    public Branch removeBranch(int branchId) throws BranchException {
        Optional<Branch> bean = branchRepository.findById(branchId);
        if (!bean.isPresent()) {
            throw new BranchException("Branch with ID " + branchId + " not found");
        }
        branchRepository.deleteById(branchId);
        return bean.get();
    }

    @Override
    public Branch updateBranch(Branch branch) throws BranchException {
        if (!branchRepository.existsById(branch.getBranchId())) {
            throw new BranchException("Branch with ID " + branch.getBranchId() + " not found");
        }
        try {
            return branchRepository.saveAndFlush(branch);
        } catch (Exception e) {
            throw new BranchException("Failed to update branch: " + e.getMessage());
        }
    }

    @Override
    public Branch getBranch(int branchId) throws BranchException {
        return branchRepository.findById(branchId)
                               .orElseThrow(() -> new BranchException("Branch with ID " + branchId + " not found"));
    }

    @Override
    public List<Branch> getAllBranchs() throws BranchException {
        try {
            return branchRepository.findAll();
        } catch (Exception e) {
            throw new BranchException("Failed to retrieve all branches: " + e.getMessage());
        }
    }
}
