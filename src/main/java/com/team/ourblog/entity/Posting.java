package com.team.ourblog.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Getter
@Entity
@NoArgsConstructor
public class Posting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createDate;

    @Column
    private String filePath;

    @Column
    private String imageUrl;

    private Long likeCount;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "posting", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "posting", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Heart> hearts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Builder
    public Posting(Long id, String title, String nickName, String content,String filePath, String imageUrl, Member member){
        this.id = id;
        this.title = title;
        this.content = content;
        this.nickName = nickName;
        this.filePath = filePath;
        this.imageUrl = imageUrl;
        this.member = member;

    }
    //== Member & Board 연관관계 편의 메소드 ==//
    public void setMappingMember(Member member) {
        this.member = member;
        member.getPostings().add(this);
    }

    // 게시물 수정
    public void update(String title, String content, String filePath, String imageUrl) {
        this.title = title;
        this.content = content;
        this.filePath = filePath;
        this.imageUrl = imageUrl;
    }


}