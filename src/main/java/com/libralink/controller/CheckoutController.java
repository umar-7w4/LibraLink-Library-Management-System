package com.libralink.controller;

import java.util.List;

import com.libralink.entity.Checkout;
import com.libralink.exception.CheckoutException;

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
import com.libralink.service.CheckoutService;

@RestController
@RequestMapping("/libralink-checkout")
public class CheckoutController {
    
    @Autowired
    CheckoutService checkoutService;
    
    @PostMapping("/add")
    public ResponseEntity<Checkout> addCheckout(@RequestBody Checkout checkout) throws CheckoutException {
        Checkout newCheckout = checkoutService.addCheckout(checkout);
        return new ResponseEntity<>(newCheckout, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{checkoutId}")
    public ResponseEntity<Void> removeCheckout(@PathVariable int checkoutId) throws CheckoutException {
        checkoutService.removeCheckout(checkoutId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Checkout> updateCheckout(@RequestBody Checkout checkout) throws CheckoutException {
        Checkout updatedCheckout = checkoutService.updateCheckout(checkout);
        return ResponseEntity.ok(updatedCheckout);
    }

    @GetMapping("/get/{checkoutId}")
    public ResponseEntity<Checkout> getCheckout(@PathVariable int checkoutId) throws CheckoutException {
        Checkout checkout = checkoutService.getCheckout(checkoutId);
        return ResponseEntity.ok(checkout);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Checkout>> getAllCheckouts() throws CheckoutException {
        List<Checkout> checkouts = checkoutService.getAllCheckouts();
        return ResponseEntity.ok(checkouts);
    }
}
