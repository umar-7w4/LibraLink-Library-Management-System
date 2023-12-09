package com.libralink.service;

import java.util.List;

import com.libralink.entity.Employee;
import com.libralink.entity.Fine;
import com.libralink.exception.BookException;
import com.libralink.exception.FineException;

public interface FineService {

	Fine addFine(Fine fine) throws FineException;
	
	Fine removeFine(int fineId) throws FineException;
	
	Fine updateFine(Fine fine) throws FineException;
	
	Fine getFine(int fineId) throws FineException;
	
	List<Fine> getAllFines() throws FineException;
}
