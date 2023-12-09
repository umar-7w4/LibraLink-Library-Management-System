package com.libralink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.libralink.entity.Checkout;
import com.libralink.exception.CheckoutException;
import com.libralink.repository.BookCopyRepository;
import com.libralink.repository.CheckoutRepository;
import com.libralink.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service("CheckoutService")
public class CheckoutServiceImplementation implements CheckoutService {
    
    @Autowired
    private CheckoutRepository checkoutRepository;
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Override
    public Checkout addCheckout(Checkout checkout) throws CheckoutException {
        try {
            checkout.setMember(memberRepository.findById(checkout.getMember().getMemberId())
                .orElseThrow(() -> new CheckoutException("Member not found")));
            checkout.setBookCopy(bookCopyRepository.findById(checkout.getBookCopy().getBookCopyId())
                .orElseThrow(() -> new CheckoutException("BookCopy not found")));
            
            return checkoutRepository.saveAndFlush(checkout);
        } catch (Exception e) {
            // Log the full stack trace for more details
            e.printStackTrace();
            throw new CheckoutException("Failed to add checkout: " + e.getMessage());
        }
    }

    @Override
    public Checkout removeCheckout(int checkoutId) throws CheckoutException {
        Optional<Checkout> checkout = checkoutRepository.findById(checkoutId);
        if (!checkout.isPresent()) {
            throw new CheckoutException("Checkout with ID " + checkoutId + " not found");
        }
        checkoutRepository.deleteById(checkoutId);
        return checkout.get();
    }

    @Override
    public Checkout updateCheckout(Checkout checkout) throws CheckoutException {
        if (!checkoutRepository.existsById(checkout.getCheckoutId())) {
            throw new CheckoutException("Checkout with ID " + checkout.getCheckoutId() + " not found");
        }
        try {
            checkout.setMember(memberRepository.findById( checkout.getMember().getMemberId())
                .orElseThrow(() -> new CheckoutException("Member not found")));
            checkout.setBookCopy(bookCopyRepository.findById(checkout.getBookCopy().getBookCopyId())
                .orElseThrow(() -> new CheckoutException("BookCopy not found")));
            
            return checkoutRepository.saveAndFlush(checkout);
        } catch (Exception e) {
            throw new CheckoutException("Failed to update checkout: " + e.getMessage());
        }
    }

    @Override
    public Checkout getCheckout(int checkoutId) throws CheckoutException {
        return checkoutRepository.findById(checkoutId)
                                 .orElseThrow(() -> new CheckoutException("Checkout with ID " + checkoutId + " not found"));
    }

    @Override
    public List<Checkout> getAllCheckouts() throws CheckoutException {
        try {
            return checkoutRepository.findAll();
        } catch (Exception e) {
            throw new CheckoutException("Failed to retrieve all checkouts: " + e.getMessage());
        }
    }
}
