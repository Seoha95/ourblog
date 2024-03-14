package com.team.ourblog.dto.request.posting;

import com.team.ourblog.entity.Posting;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostingRequestDto {

    @NotBlank
    private String nickname;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private Long categoryId;


    @Builder
    public static Posting ofEntity(PostingRequestDto requestDto) {
        return Posting.builder()
                .nickName(requestDto.nickname)
                .title(requestDto.title)
                .content(requestDto.content)
                .categoryId(requestDto.categoryId)
                .build();
    }
}
