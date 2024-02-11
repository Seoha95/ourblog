package com.team.ourblog.dto.request.posting;

import com.team.ourblog.entity.Posting;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostingRequestDto {

    @NotNull
    private String nickname;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
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
