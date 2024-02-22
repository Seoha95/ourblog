package com.team.ourblog.dto.response.comment;

import com.team.ourblog.entity.Comment;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDto {

    private Long commentId;
    private String reply;
    private String createdDate;
    private String author;
    private String imageUrl;
    private String email;

    public static CommentResponseDto fromEntity(Comment comment) {
        return CommentResponseDto.builder()
                .commentId(comment.getId())
                .reply(comment.getReply())
                .createdDate(String.valueOf(comment.getCreatedDate()))
                .author(comment.getMember().getNickname())
                .imageUrl(comment.getMember().getImage().getUrl())
                .email(comment.getMember().getEmail())
                .build();

    }
}
