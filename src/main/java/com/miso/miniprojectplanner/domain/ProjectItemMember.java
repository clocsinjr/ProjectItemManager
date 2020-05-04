package com.miso.miniprojectplanner.domain;

import com.miso.miniprojectplanner.domain.member.Member;

import javax.persistence.*;

@Entity
public class ProjectItemMember {
    public enum Status {REQUESTED, ACCEPTED, ASSIGNED;}

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String role;
    private Status status = Status.REQUESTED;

    @ManyToOne
    private Member member;

    public ProjectItemMember(){}
    public ProjectItemMember(String role, Member member){
        this.role = role;
        this.member = member;
    }


    public long getId() { return id; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }

    @PreRemove
    public void dismissMember(){
        System.out.println("[PreRemove] Removed link from ProjectItemMember.member");
        this.member = null;
    }
}
