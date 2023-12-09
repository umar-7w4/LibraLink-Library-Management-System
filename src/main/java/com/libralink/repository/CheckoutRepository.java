package com.libralink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libralink.entity.Branch;
import com.libralink.entity.Checkout;

@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, Integer>{

}
