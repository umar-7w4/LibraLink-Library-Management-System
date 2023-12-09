package com.libralink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libralink.entity.Inventory;
import com.libralink.entity.Member;
import java.util.*;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // Method to find members by first or last name, case-insensitive
    List<Member> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
}