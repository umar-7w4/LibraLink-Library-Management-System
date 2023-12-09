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

import com.libralink.entity.BookCopy;
import com.libralink.entity.Branch;
import com.libralink.exception.BookCopyException;
import com.libralink.exception.BranchException;
import com.libralink.service.BookCopyService;
import com.libralink.service.BranchService;

@RestController
@RequestMapping("/libralink-branch")
public class BranchController {
    
    @Autowired
    BranchService branchService;
    
    @PostMapping("/add")
    public ResponseEntity<Branch> addBranch(@RequestBody Branch branch) throws BranchException {
        Branch newBranch = branchService.addBranch(branch);
        return new ResponseEntity<>(newBranch, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{branchId}")
    public ResponseEntity<Void> removeBranch(@PathVariable int branchId) throws BranchException {
        branchService.removeBranch(branchId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Branch> updateBranch(@RequestBody Branch branch) throws BranchException {
        Branch updatedBranch = branchService.updateBranch(branch);
        return ResponseEntity.ok(updatedBranch);
    }

    @GetMapping("/get/{branchId}")
    public ResponseEntity<Branch> getBranch(@PathVariable int branchId) throws BranchException {
        Branch branch = branchService.getBranch(branchId);
        return ResponseEntity.ok(branch);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Branch>> getAllBranchs() throws BranchException {
        List<Branch> branches = branchService.getAllBranchs();
        return ResponseEntity.ok(branches);
    }
}
