package com.libralink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libralink.entity.Employee;
import com.libralink.entity.Fine;
import com.libralink.exception.EmployeeException;
import com.libralink.exception.FineException;
import com.libralink.repository.BookCopyRepository;
import com.libralink.repository.FineRepository;
import com.libralink.repository.MemberRepository;

@Service("FineService")
public class FineServiceImplementation implements FineService {
    
    @Autowired
    private FineRepository fineRepository;
    
    @Autowired
    private MemberRepository memberRepository;
    
    @Autowired
    private BookCopyRepository bookCopyRepository;

    @Override
    public Fine addFine(Fine fine) throws FineException {
        try {
            fine.setMember(memberRepository.findById((long) fine.getMember().getMemberId())
                .orElseThrow(() -> new FineException("Member not found")));
            fine.setBookCopy(bookCopyRepository.findById(fine.getBookCopy().getBookCopyId())
                .orElseThrow(() -> new FineException("BookCopy not found")));

            return fineRepository.saveAndFlush(fine);
        } catch (Exception e) {
            throw new FineException("Failed to add fine: " + e.getMessage());
        }
    }

    @Override
    public Fine removeFine(int fineId) throws FineException {
        return fineRepository.findById(fineId)
            .map(fine -> {
                fineRepository.deleteById(fineId);
                return fine;
            })
            .orElseThrow(() -> new FineException("Fine with ID " + fineId + " not found"));
    }

    @Override
    public Fine updateFine(Fine fine) throws FineException {
        if (!fineRepository.existsById(fine.getFineId())) {
            throw new FineException("Fine with ID " + fine.getFineId() + " not found");
        }
        try {
            fine.setMember(memberRepository.findById((long) fine.getMember().getMemberId())
                .orElseThrow(() -> new FineException("Member not found")));
            fine.setBookCopy(bookCopyRepository.findById(fine.getBookCopy().getBookCopyId())
                .orElseThrow(() -> new FineException("BookCopy not found")));

            return fineRepository.saveAndFlush(fine);
        } catch (Exception e) {
            throw new FineException("Failed to update fine: " + e.getMessage());
        }
    }

    @Override
    public Fine getFine(int fineId) throws FineException {
        return fineRepository.findById(fineId)
            .orElseThrow(() -> new FineException("Fine with ID " + fineId + " not found"));
    }

    @Override
    public List<Fine> getAllFines() throws FineException {
        try {
            return fineRepository.findAll();
        } catch (Exception e) {
            throw new FineException("Failed to retrieve all fines: " + e.getMessage());
        }
    }
}
