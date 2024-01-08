package com.team.ourblog.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Entity
@NoArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "like_cnt")
    private Long likeCnt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FileEntity> files = new ArrayList<>();

    @Builder
    public Blog(Long id, String title, String content, Date createDate, Member member, List<FileEntity> files, Long likeCnt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.member = member;
        this.files = files;
        this.likeCnt = likeCnt;
    }
    // Member & Blog 연관관계 편의 메소드
    public void setMappingMember(Member member) {
        this.member = member;
        member.getBlogList().add(this);
    }

    // FileEntity & Blog 연관관계 편의 메소드
    public void setMappingFiles(List<FileEntity> files) {
        if (files != null && !files.isEmpty()) {
            this.files.addAll(files);
        }
    }
}