package com.miso.miniprojectplanner.view;

import com.miso.miniprojectplanner.controller.ProjectItemService;
import com.miso.miniprojectplanner.domain.ProjectItemMember;
import com.miso.miniprojectplanner.domain.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("member")
public class MemberEndpoint {
    @Autowired
    ProjectItemService piService;

    @GetMapping("/getMemberId")
    public Member getMemberById(@RequestParam long memId){
        return piService.findMemberById(memId);
    }

    @GetMapping("/getMemberName")
    public Member getMemberByName(@RequestParam String name){
        return piService.findMemberByName(name);
    }
}
