package com.libralink.controller;

import java.util.List;

import com.libralink.entity.Inventory;
import com.libralink.exception.InventoryException;


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
import com.libralink.service.InventoryService;

@RestController
@RequestMapping("/libralink-inventory")
public class InventoryController {
    
    @Autowired
    InventoryService inventoryService;
    
    @PostMapping("/add")
    public ResponseEntity<Inventory> addInventory(@RequestBody Inventory inventory) throws InventoryException {
        Inventory newInventory = inventoryService.addInventory(inventory);
        return new ResponseEntity<>(newInventory, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{inventoryId}")
    public ResponseEntity<Void> removeInventory(@PathVariable int inventoryId) throws InventoryException {
        inventoryService.removeInventory(inventoryId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Inventory> updateInventory(@RequestBody Inventory inventory) throws InventoryException {
        Inventory updatedInventory = inventoryService.updateInventory(inventory);
        return ResponseEntity.ok(updatedInventory);
    }

    @GetMapping("/get/{inventoryId}")
    public ResponseEntity<Inventory> getInventory(@PathVariable int inventoryId) throws InventoryException {
        Inventory inventory = inventoryService.getInventory(inventoryId);
        return ResponseEntity.ok(inventory);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Inventory>> getAllInventoriess() throws InventoryException {
        List<Inventory> inventories = inventoryService.getAllInventoriess();
        return ResponseEntity.ok(inventories);
    }
}
