package com.libralink.service;

import java.util.List;

import com.libralink.entity.JobRole;
import com.libralink.entity.Member;
import com.libralink.exception.BookException;
import com.libralink.exception.MemberException;

public interface MemberService {

	Member addMember(Member member) throws MemberException;
	
	Member removeMember(int memberId) throws MemberException;
	
	Member updateMember(Member member) throws MemberException;
	
	Member getMember(int memberId) throws MemberException;
	
	List<Member> getAllMembers() throws MemberException;
	
	long countAllMembers() throws MemberException;

	List<Member> searchMembers(String query);
}
