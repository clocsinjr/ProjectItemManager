package com.miso.miniprojectplanner.domain;

import com.miso.miniprojectplanner.domain.member.Member;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class ProjectItem {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String title;
    @Size(max=1000)
    private String description;

    private ArrayList<String> allowedRoles = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectItemMember> members;

    public boolean hasMember(Member m){
        for(ProjectItemMember piMem : this.members){
            if (piMem.getMember() == m){
                return true;
            }
        }
        return false;
    }

    public long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public ArrayList<String> getAllowedRoles() { return allowedRoles; }
    public void setAllowedRoles(ArrayList<String> allowedRoles) { this.allowedRoles = allowedRoles; }
    public void addAllowedRoles(String newRole){
        this.allowedRoles.add(newRole);
    }

    public List<ProjectItemMember> getMembers() { return members; }
    public void addPiMember(ProjectItemMember mem) {
        this.members.add(mem);
    }
    public void removePiMember(ProjectItemMember mem){
        this.members.remove(mem);
    }

    @PreRemove
    public void dismissMembers(){
        System.out.println("[PreRemove] removed links to projectItem.members");
        this.members.clear();
    }
}
