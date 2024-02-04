package com.team.ourblog.dto.response.comment;

import com.team.ourblog.entity.Comment;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentUpdateDto {

    private Long commentId;
    private String reply;
    private String createdDate;
    private String author;

    public static CommentUpdateDto fromEntity(Comment comment){
        return CommentUpdateDto.builder()
                .commentId(comment.getId())
                .reply(comment.getReply())
                .createdDate(comment.getCreatedDate())
                .author(comment.getMember().getNickname())
                .build();
    }

}
