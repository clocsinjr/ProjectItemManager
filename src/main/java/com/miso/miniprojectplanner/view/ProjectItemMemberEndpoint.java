package com.miso.miniprojectplanner.view;

import com.miso.miniprojectplanner.controller.ProjectItemService;
import com.miso.miniprojectplanner.domain.ProjectItemMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("projectItemMember")
public class ProjectItemMemberEndpoint {
    @Autowired
    ProjectItemService piService;

    @PostMapping("/memberChangeRole")
    public String memberChangeRole(@RequestParam long piMemId, @RequestParam String newRole){
        ProjectItemMember piMem = piService.findProjectItemMemberById(piMemId);
        piMem.setRole(newRole);
        piService.saveProjectItemMember(piMem);
        return "Role changed succesfully";
    }
    @PostMapping("/memberChangeStatus")
    public String memberChangeStatus(@RequestParam long piMemId, @RequestParam ProjectItemMember.Status newStatus){
        ProjectItemMember piMem = piService.findProjectItemMemberById(piMemId);
        piMem.setStatus(newStatus);
        piService.saveProjectItemMember(piMem);
        return "status changed succesfully";
    }
}
