package com.team.ourblog.dto.request.posting;

import com.team.ourblog.entity.Posting;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostingRequestDto {
    private String title;
    private String content;
    private String filePath;
    private String imageUrl;
    private Long likeCnt;

    @Builder
    public static Posting ofEntity(PostingRequestDto requestDto) {
        return Posting.builder()
                .title(requestDto.title)
                .content(requestDto.content)
                .likeCnt(requestDto.likeCnt)
                .filePath(requestDto.filePath)
                .imageUrl(requestDto.imageUrl)
                .build();
    }
}
