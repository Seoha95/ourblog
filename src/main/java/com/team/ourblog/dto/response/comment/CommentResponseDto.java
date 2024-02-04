package com.team.ourblog.dto.response.comment;

import com.team.ourblog.entity.Comment;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDto {

    private Long commentId;
    private String reply;
    private Date createDate;
    private String author;
    private String imageUrl;

    public static CommentResponseDto fromEntity(Comment comment) {
        return CommentResponseDto.builder()
                .commentId(comment.getId())
                .reply(comment.getReply())
                .createDate(comment.getCreateDate())
                .author(comment.getMember().getNickname())
                .imageUrl(comment.getMember().getImage().getUrl())
                .build();

    }
}
