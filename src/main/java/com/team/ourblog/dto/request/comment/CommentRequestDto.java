package com.team.ourblog.dto.request.comment;

import com.team.ourblog.entity.Comment;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {

    private String reply;

    @Builder
    public static Comment ofEntity(CommentRequestDto requestDto){
        return Comment.builder()
                .reply(requestDto.getReply())
                .build();
    }
}
