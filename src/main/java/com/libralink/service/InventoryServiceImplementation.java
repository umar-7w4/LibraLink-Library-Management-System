package com.libralink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libralink.entity.Fine;
import com.libralink.entity.Inventory;
import com.libralink.exception.FineException;
import com.libralink.exception.InventoryException;
import com.libralink.repository.BookRepository;
import com.libralink.repository.BranchRepository;
import com.libralink.repository.InventoryRepository;

@Service("InventoryService")
public class InventoryServiceImplementation implements InventoryService {
    
    @Autowired
    private InventoryRepository inventoryRepository;
    
    @Autowired
    private BranchRepository branchRepository;
    
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Inventory addInventory(Inventory inventory) throws InventoryException {
        try {
            inventory.setBranch(branchRepository.findById(inventory.getBranch().getBranchId())
                .orElseThrow(() -> new InventoryException("Branch not found")));
            inventory.setBook(bookRepository.findById(inventory.getBook().getBookId())
                .orElseThrow(() -> new InventoryException("Book not found")));
            
            return inventoryRepository.saveAndFlush(inventory);
        } catch (Exception e) {
            throw new InventoryException("Failed to add inventory: " + e.getMessage());
        }
    }

    @Override
    public Inventory removeInventory(int inventoryId) throws InventoryException {
        return inventoryRepository.findById(inventoryId)
            .map(inventory -> {
                inventoryRepository.deleteById(inventoryId);
                return inventory;
            })
            .orElseThrow(() -> new InventoryException("Inventory with ID " + inventoryId + " not found"));
    }

    @Override
    public Inventory updateInventory(Inventory inventory) throws InventoryException {
        if (!inventoryRepository.existsById(inventory.getInventoryId())) {
            throw new InventoryException("Inventory with ID " + inventory.getInventoryId() + " not found");
        }
        try {
            inventory.setBranch(branchRepository.findById(inventory.getBranch().getBranchId())
                .orElseThrow(() -> new InventoryException("Branch not found")));
            inventory.setBook(bookRepository.findById(inventory.getBook().getBookId())
                .orElseThrow(() -> new InventoryException("Book not found")));
            
            return inventoryRepository.saveAndFlush(inventory);
        } catch (Exception e) {
            throw new InventoryException("Failed to update inventory: " + e.getMessage());
        }
    }

    @Override
    public Inventory getInventory(int inventoryId) throws InventoryException {
        return inventoryRepository.findById(inventoryId)
            .orElseThrow(() -> new InventoryException("Inventory with ID " + inventoryId + " not found"));
    }

    @Override
    public List<Inventory> getAllInventoriess() throws InventoryException {
        try {
            return inventoryRepository.findAll();
        } catch (Exception e) {
            throw new InventoryException("Failed to retrieve all inventories: " + e.getMessage());
        }
    }
}