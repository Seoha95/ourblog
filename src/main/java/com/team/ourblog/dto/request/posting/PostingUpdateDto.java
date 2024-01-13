package com.team.ourblog.dto.request.posting;

import com.team.ourblog.entity.Posting;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class PostingUpdateDto {

    private String title;
    private String content;
    private String filePath;
    private String imageUrl;

    @Builder
    public static Posting ofEntity(PostingUpdateDto requestDto) {
        return Posting.builder()
                .title(requestDto.title)
                .content(requestDto.content)
                .filePath(requestDto.filePath)
                .imageUrl(requestDto.imageUrl)
                .build();
    }
}