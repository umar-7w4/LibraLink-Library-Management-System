package com.libralink.service;

import java.util.List;

import com.libralink.entity.Fine;
import com.libralink.entity.Inventory;
import com.libralink.exception.BookException;
import com.libralink.exception.InventoryException;

public interface InventoryService {
	
	Inventory addInventory(Inventory inventory) throws InventoryException;
	
	Inventory removeInventory(int inventoryId) throws InventoryException;
	
	Inventory updateInventory(Inventory inventory) throws InventoryException;
	
	Inventory getInventory(int inventoryId) throws InventoryException;
	
	List<Inventory> getAllInventoriess() throws InventoryException;

}
