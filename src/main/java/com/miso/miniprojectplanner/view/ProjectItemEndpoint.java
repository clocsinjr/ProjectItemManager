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
    public String makeProjectItem(@RequestBody ProjectItem project){
        System.out.println("> in makeProjectItem(): " + project.toString());
        piService.saveProjectItem(project);
        return "created new projectItem";
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

    @PostMapping("/removeMember")
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

    @PostMapping("initTestTable")
    public void initTestTable(){
        piService.saveMember(new Member("AAA"));
        piService.saveMember(new Member("BBB"));
        piService.saveMember(new Member("Carlo"));
        piService.saveMember(new Member("DDD"));

        ProjectItem newProject = new ProjectItem();
        newProject.setTitle("Project initialized by initTestTable");
        newProject.setDescription("this project has been initialized by a developer endpoint to test more effectively");
        piService.saveProjectItem(newProject);

        newProject = new ProjectItem();
        newProject.setTitle("2nd Project");
        newProject.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum nisl mi, semper " +
                "at ex in, molestie dapibus tortor. Phasellus nisl ipsum, dictum quis suscipit nec, faucibus vitae quam. " +
                "Nunc iaculis neque at leo ultrices venenatis. Maecenas lobortis enim eleifend, elementum sem ut, " +
                "imperdiet nisi. Fusce quam eros, vestibulum non tincidunt non, commodo in ex. Donec interdum condimentum " +
                "quam, ac volutpat enim facilisis vel. Proin luctus metus mollis est dictum volutpat. Vestibulum ante " +
                "ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Ut egestas quam lacus.");
        newProject.setAllowedRoles(new ArrayList<>(Arrays.asList("main", "backup", "support")));
        piService.saveProjectItem(newProject);
    }

}
