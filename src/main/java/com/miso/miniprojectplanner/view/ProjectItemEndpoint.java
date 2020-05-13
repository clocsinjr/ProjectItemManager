package com.miso.miniprojectplanner.view;

import com.miso.miniprojectplanner.controller.ProjectItemService;
import com.miso.miniprojectplanner.domain.ProjectItem;
import com.miso.miniprojectplanner.domain.ProjectItemMember;
import com.miso.miniprojectplanner.domain.member.Member;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("projectItem")
public class ProjectItemEndpoint {
    @Autowired
    ProjectItemService piService;

    @GetMapping(path = "/{mpid}")
    public ProjectItem getProjectItem(@PathVariable long mpid) {
        return piService.findProjectItemById(mpid);
    }

    @GetMapping("/allProjectItems")
    public Iterable<ProjectItem> viewProjectItem(){
        System.out.println("> entered: viewProjectItem()");
        return piService.findAllProjectItems();
    }

    @PostMapping("/makeProjectItem")
    public void makeProjectItem(@RequestBody ProjectItem project){
        System.out.println("> in makeProjectItem(): " + project.toString());
        piService.saveProjectItem(project);
    }

    @GetMapping("getAllMembers")
    public Iterable<Member> getAllMembers(){
        return piService.getAllMembers();
    }

    @GetMapping("getMember")
    public Member getMember(@RequestParam long memId){
        return piService.findMemberById(memId);
    }
    @PostMapping("/addMember")
    public String addMember(@RequestParam long projId, @RequestParam long memId, @RequestParam String role){
        ProjectItem project = piService.findProjectItemById(projId);
        Member member = piService.findMemberById(memId);
        if(project.hasMember(member)){
            return "You are already added to the project!";
        }

        piService.addProjectItemMember(project, member, role);
        return "succesfully added '" + member.getName() + "' to project '" + project.getTitle() + "'";
    }

    @DeleteMapping("/removeMember")
    public String removeMember(@RequestParam long projId, @RequestParam long memId){
        ProjectItem project = piService.findProjectItemById(projId);
        Member member = piService.findMemberById(memId);
        if(project.hasMember(member) == false){
            return "You are not a member of the project!";
        }

        piService.removeProjectItemMember(project, member);
        return "succesfully removed '" + member.getName() + "' from project '" + project.getTitle() + "'";
    }

    @DeleteMapping(path = "/deleteProjectItem")
    public String deleteProjectItem(@RequestParam long mpid) {
        System.out.println("> delete project " + mpid);
        ProjectItem project = getProjectItem(mpid);
        piService.deleteProjectItem(project);
        return "deleted project with id '" + mpid + "'";
    }

    @PostMapping("/initTestTable")
    public void initTestTable(){
        System.out.println("initiating table");
        Member memA = new Member("AAA");
        Member memB = new Member("BBB");
        Member memCarlo = new Member("Carlo");
        Member memD = new Member("DDD");
        Member leader = new Member("Leader Account");
        leader.setLevel(Member.Rights.LEADER);

        piService.saveMember(memA);
        piService.saveMember(memB);
        piService.saveMember(memCarlo);
        piService.saveMember(memD);
        piService.saveMember(leader);

        ProjectItem newProject1 = new ProjectItem();
        newProject1.setTitle("Dance Example: Choreography cover");
        newProject1.setDescription("Cover choreography by 1MILLION Dance Studio, Ed Sheeran - Galway Girl");
        newProject1.setAllowedRoles(new ArrayList<>(Arrays.asList("lead dancer", "main dancer", "background dancer")));
        piService.saveProjectItem(newProject1);


        ProjectItem newProject2 = new ProjectItem();
        newProject2.setTitle("Project initialized by initTestTable");
        newProject2.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nisl mi, semper " +
                "at ex in, molestie dapibus tortor. Phasellus nisl ipsum, dictum quis suscipit nec, faucibus vitae quam. " +
                "Nunc iaculis neque at leo ultrices venenatis. Maecenas lobortis enim eleifend, elementum sem ut, " +
                "imperdiet nisi. Fusce quam eros, vestibulum non tincidunt non, commodo in ex. Donec interdum condimentum " +
                "quam, ac volutpat enim facilisis vel. Proin luctus metus mollis est dictum volutpat. Vestibulum ante " +
                "ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Ut egestas quam lacus.");

        newProject2.setAllowedRoles(new ArrayList<>(Arrays.asList("Role 1", "Role 2", "Role 3")));
        piService.saveProjectItem(newProject2);
    }
}
