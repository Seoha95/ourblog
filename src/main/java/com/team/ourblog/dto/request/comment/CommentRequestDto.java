package com.team.ourblog.dto.request.comment;

import com.team.ourblog.entity.Comment;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {

    private String reply;

    public static Comment ofEntity(CommentRequestDto requestDto){
        return Comment.builder()
                .reply(requestDto.getReply())
                .build();
    }
}
