package com.miso.miniprojectplanner.domain.member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Member {
    public enum Rights {MEMBER, LEADER;}

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;
    private Rights level = Rights.MEMBER;

    public Member(){}
    public Member(String name){
        this.name = name;
    }
    public long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Rights getLevel() { return level; }
    public void setLevel(Rights level) { this.level = level; }
}
