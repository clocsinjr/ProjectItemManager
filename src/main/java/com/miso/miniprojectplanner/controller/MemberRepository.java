package com.miso.miniprojectplanner.controller;

import com.miso.miniprojectplanner.domain.member.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface MemberRepository extends CrudRepository<Member, Long> {
    Optional<Member> findByName(String name);
}
