package com.team.ourblog.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private  Long id;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Blog> blogList = new ArrayList<>();



    @Builder
    public Member(Long id,String email,String nickname,String name, String password, Authority authority) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.name = name;
        this.password = password;
        this.authority = authority;

    }


}
