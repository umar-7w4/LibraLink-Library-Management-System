package com.libralink.service;

import java.util.List;

import com.libralink.entity.Checkout;
import com.libralink.exception.BookException;
import com.libralink.exception.CheckoutException;

public interface CheckoutService {
	
	Checkout addCheckout(Checkout checkout) throws CheckoutException;
	
	Checkout removeCheckout(int checkoutId) throws CheckoutException;
	
	Checkout updateCheckout(Checkout checkout) throws CheckoutException;
	
	Checkout getCheckout(int checkoutId) throws CheckoutException;
	
	List<Checkout> getAllCheckouts() throws CheckoutException;

}
