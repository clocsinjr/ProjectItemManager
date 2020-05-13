package com.miso.miniprojectplanner.controller;

import com.miso.miniprojectplanner.domain.ProjectItem;
import com.miso.miniprojectplanner.domain.ProjectItemMember;
import com.miso.miniprojectplanner.domain.member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectItemService {
    @Autowired
    ProjectItemRepository piRepo;
    @Autowired
    ProjectItemMemberRepository piMemRepo;
    @Autowired
    MemberRepository memRepo;

    public Member findMemberById(long id){
        Optional<Member> foundMember = memRepo.findById(id);
        if (foundMember.isPresent()){ return foundMember.get();}
        else {return null;}
    }
    public Member findMemberByName(String name){
        Optional<Member> foundMember = memRepo.findByName(name);
        if (foundMember.isPresent()){ return foundMember.get();}
        else {return null;}
    }

    public ProjectItemMember findProjectItemMemberById(long id){
        Optional<ProjectItemMember> foundPiMem = piMemRepo.findById(id);
        if (foundPiMem.isPresent())
            return foundPiMem.get();
        else
            return null;
    }
    public ProjectItem findProjectItemById(long id){
        Optional<ProjectItem> foundProject = piRepo.findById(id);
        if (foundProject.isPresent())
            return foundProject.get();
        else
            return null;
    }

    public Iterable<ProjectItem> findAllProjectItems(){
        System.out.println("> in ProjectItemService.findAllProjectItems()");
        return piRepo.findAll();
    }

    public List<ProjectItem> tryFindByName(String projName){
        return piRepo.findByTitleAllIgnoreCase(projName);
    }


    public void saveMember(Member mem){
        memRepo.save(mem);
    }
    public void saveProjectItemMember(ProjectItemMember piMem){
        piMemRepo.save(piMem);
    }
    public void saveProjectItem(ProjectItem project){
        piRepo.save(project);
    }


    public Iterable<Member> getAllMembers(){
        return memRepo.findAll();
    }
    public void addProjectItemMember(ProjectItem project, Member mem, String role){
        ProjectItemMember piMem = new ProjectItemMember(role, mem);
        piMem.setRole(role);
        piMem.setMember(mem);
        piMemRepo.save(piMem);
        project.addPiMember(piMem);

        piRepo.save(project);
    }
    public void removeProjectItemMember(ProjectItem project, Member mem){
        for(ProjectItemMember piMem : new ArrayList<ProjectItemMember>(project.getMembers())){
            if (piMem.getMember().getId() == mem.getId()){
                System.out.println("removing in: ProjectItemService, removeProjectItemMember()");
                project.removePiMember(piMem);

                Optional<ProjectItemMember> foundPiMem = piMemRepo.findById(piMem.getId());
                if (foundPiMem.isPresent())
                    piMemRepo.delete(foundPiMem.get());

                piRepo.save(project);
            }
        }

    }
    public void deleteProjectItem(ProjectItem project){
        System.out.println("deleting project item with id '" + project.getId() + "'");
        piRepo.delete(project);
    }
}
