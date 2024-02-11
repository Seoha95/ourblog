package com.team.ourblog.dto.request.posting;

import com.team.ourblog.entity.Posting;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostingRequestDto {
    private String nickname;
    private String title;
    private String content;
    private String categoryId;


    @Builder
    public static Posting ofEntity(PostingRequestDto requestDto) {
        return Posting.builder()
                .nickName(requestDto.nickname)
                .title(requestDto.title)
                .content(requestDto.content)
                .categoryId(Long.parseLong(requestDto.categoryId))
                .build();
    }
}
