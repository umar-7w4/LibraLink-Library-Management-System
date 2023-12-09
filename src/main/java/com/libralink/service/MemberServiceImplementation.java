package com.libralink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libralink.entity.JobRole;
import com.libralink.entity.Member;
import com.libralink.exception.EmployeeException;
import com.libralink.exception.JobRoleException;
import com.libralink.exception.MemberException;
import com.libralink.repository.MemberRepository;

@Service("MemberService")
public class MemberServiceImplementation implements MemberService {
    
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public Member addMember(Member member) throws MemberException {
        try {
            return memberRepository.saveAndFlush(member);
        } catch (Exception e) {
            throw new MemberException("Failed to add member: " + e.getMessage());
        }
    }

    @Override
    public Member removeMember(int memberId) throws MemberException {
        return memberRepository.findById((long) memberId)
            .map(member -> {
                memberRepository.deleteById((long) memberId);
                return member;
            })
            .orElseThrow(() -> new MemberException("Member with ID " + memberId + " not found"));
    }

    @Override
    public Member updateMember(Member member) throws MemberException {
        if (!memberRepository.existsById(member.getMemberId())) {
            throw new MemberException("Member with ID " + member.getMemberId() + " not found");
        }
        return memberRepository.saveAndFlush(member);
    }

    @Override
    public Member getMember(int memberId) throws MemberException {
        return memberRepository.findById((long) memberId)
            .orElseThrow(() -> new MemberException("Member with ID " + memberId + " not found"));
    }

    @Override
    public List<Member> getAllMembers() throws MemberException {
        try {
            return memberRepository.findAll();
        } catch (Exception e) {
            throw new MemberException("Failed to retrieve all members: " + e.getMessage());
        }
    }  
    
    @Override
    public long countAllMembers() throws MemberException {
        try {
            return memberRepository.count();
        } catch (Exception e) {
            throw new MemberException("Failed to count all members: " + e.getMessage());
        }
    }
    
    @Override
    public List<Member> searchMembers(String query) {
        return memberRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(query, query);
    }
}
