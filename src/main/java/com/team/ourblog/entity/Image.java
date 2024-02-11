package com.team.ourblog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Entity
@AllArgsConstructor
public class Image {

    protected Image(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="IMAGE_ID")
    private Long id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private String url;

    public void updateUrl(String url){
        this.url = url;
    }
}
