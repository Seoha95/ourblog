package com.team.ourblog.entity;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;


}
