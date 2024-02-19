package com.team.ourblog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Entity
@Builder
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    protected Comment(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_ID")
    private Long id;

    private String reply;

    @CreatedDate
    private String createdDate = LocalDateTime.now().plusHours(9).format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Posting posting;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void setPosting(Posting posting){
        this.posting = posting;
        posting.getComments().add(this);
    }
    public void setMember(Member member){
        this.member = member;
        member.getComments().add(this);
    }

    public void update(String reply) {
        this.reply = reply;
    }
}
