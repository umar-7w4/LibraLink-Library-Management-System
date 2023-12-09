package com.libralink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libralink.entity.Fine;
import com.libralink.entity.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer>{

}
