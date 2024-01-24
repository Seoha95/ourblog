package com.team.ourblog.dto.request.posting;

import com.team.ourblog.entity.Posting;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class PostingUpdateDto {

    private String title;
    private String content;

    @Builder
    public static Posting ofEntity(PostingUpdateDto requestDto) {
        return Posting.builder()
                .title(requestDto.title)
                .content(requestDto.content)
                .build();
    }
}
