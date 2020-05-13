package com.miso.miniprojectplanner.view;

import com.miso.miniprojectplanner.controller.ProjectItemService;
import com.miso.miniprojectplanner.domain.ProjectItemMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("projectItemMember")
public class ProjectItemMemberEndpoint {
    @Autowired
    ProjectItemService piService;

    @PutMapping("/memberChangeRole")
    public String memberChangeRole(@RequestParam long piMemId, @RequestParam String newRole){
        ProjectItemMember piMem = piService.findProjectItemMemberById(piMemId);
        piMem.setRole(newRole);
        piService.saveProjectItemMember(piMem);
        return "Role changed succesfully";
    }
    @PutMapping("/memberChangeStatus")
    public String memberChangeStatus(@RequestParam long piMemId, @RequestParam ProjectItemMember.Status newStatus){
        ProjectItemMember piMem = piService.findProjectItemMemberById(piMemId);

        if (newStatus == ProjectItemMember.Status.ASSIGNED && piMem.getRole() == ""){
            return "status unchanged: Can't assign member to project without specifying the member's role";
        }
        piMem.setStatus(newStatus);
        piService.saveProjectItemMember(piMem);
        return "status changed succesfully";
    }
}
