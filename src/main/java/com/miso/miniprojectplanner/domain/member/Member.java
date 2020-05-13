package com.miso.miniprojectplanner.domain.member;

import javax.persistence.*;

@Entity
public class Member {
    public enum Rights {MEMBER, LEADER;}

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(unique=true)
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
