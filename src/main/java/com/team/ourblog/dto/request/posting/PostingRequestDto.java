package com.team.ourblog.dto.request.posting;

import com.team.ourblog.entity.Posting;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostingRequestDto {
    private String nickName;
    private String title;
    private String content;
    private Long categoryId;


    @Builder
    public static Posting ofEntity(PostingRequestDto requestDto) {
        return Posting.builder()
                .nickName(requestDto.nickName)
                .title(requestDto.title)
                .content(requestDto.content)
                .categoryId(requestDto.categoryId)
                .build();
    }
}
