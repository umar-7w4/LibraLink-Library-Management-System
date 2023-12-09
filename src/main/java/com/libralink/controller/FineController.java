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

import com.libralink.entity.Fine;
import com.libralink.exception.FineException;
import com.libralink.service.FineService;

@RestController
@RequestMapping("/libralink-fine")
public class FineController {
    
    @Autowired
    FineService fineService;
    
    @PostMapping("/add")
    public ResponseEntity<Fine> addFine(@RequestBody Fine fine) throws FineException {
        Fine newFine = fineService.addFine(fine);
        return new ResponseEntity<>(newFine, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{fineId}")
    public ResponseEntity<Void> removeFine(@PathVariable int fineId) throws FineException {
        fineService.removeFine(fineId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Fine> updateFine(@RequestBody Fine fine) throws FineException {
        Fine updatedFine = fineService.updateFine(fine);
        return ResponseEntity.ok(updatedFine);
    }

    @GetMapping("/get/{fineId}")
    public ResponseEntity<Fine> getFine(@PathVariable int fineId) throws FineException {
        Fine fine = fineService.getFine(fineId);
        return ResponseEntity.ok(fine);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Fine>> getAllFines() throws FineException {
        List<Fine> fines = fineService.getAllFines();
        return ResponseEntity.ok(fines);
    }
}
