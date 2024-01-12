package com.team.ourblog.dto.response.comment;

import com.team.ourblog.entity.Comment;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentUpdateDto {

    private Long commentId;
    private String reply;
    private Date createDate;
    private String author;

    public static CommentUpdateDto fromEntity(Comment comment){
        return CommentUpdateDto.builder()
                .commentId(comment.getId())
                .reply(comment.getReply())
                .createDate(comment.getCreateDate())
                .author(comment.getMember().getNickname())
                .build();
    }

}
