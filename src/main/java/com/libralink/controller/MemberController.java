package com.libralink.controller;

import java.util.Collections;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.libralink.entity.Member;
import com.libralink.exception.EmployeeException;
import com.libralink.exception.MemberException;
import com.libralink.service.MemberService;


@RestController
@RequestMapping("/libralink-member")
public class MemberController {
    
    @Autowired
    MemberService memberService;
    
    @PostMapping("/add")
    public ResponseEntity<Member> addMember(@RequestBody Member member) throws MemberException {
        Member newMember = memberService.addMember(member);
        return new ResponseEntity<>(newMember, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{memberId}")
    public ResponseEntity<Void> removeMember(@PathVariable int memberId) throws MemberException {
        memberService.removeMember(memberId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Member> updateMember(@RequestBody Member member) throws MemberException {
        Member updatedMember = memberService.updateMember(member);
        return ResponseEntity.ok(updatedMember);
    }

    @GetMapping("/get/{memberId}")
    public ResponseEntity<Member> getMember(@PathVariable int memberId) throws MemberException {
        Member member = memberService.getMember(memberId);
        return ResponseEntity.ok(member);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Member>> getAllMembers() throws MemberException {
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }
    
    
    @GetMapping("/members/count")
    public ResponseEntity<?> getMembersCount() throws MemberException {
        long count = memberService.countAllMembers();
        return ResponseEntity.ok(Collections.singletonMap("count", count));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Member>> searchMembers(@RequestParam String query) {
        List<Member> members = memberService.searchMembers(query);
        return ResponseEntity.ok(members);
    }
}
