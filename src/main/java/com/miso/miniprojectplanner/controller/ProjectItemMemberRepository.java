package com.miso.miniprojectplanner.controller;

import com.miso.miniprojectplanner.domain.ProjectItemMember;
import com.miso.miniprojectplanner.domain.member.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProjectItemMemberRepository extends CrudRepository<ProjectItemMember, Long> {
    ProjectItemMember findByMember(Member member);
}
