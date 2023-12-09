package com.libralink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libralink.entity.Fine;

@Repository
public interface FineRepository extends JpaRepository<Fine, Integer>{

}
