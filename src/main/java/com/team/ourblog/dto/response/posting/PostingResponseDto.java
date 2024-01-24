package com.team.ourblog.dto.response.posting;

import com.team.ourblog.entity.Posting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PostingResponseDto {
    private Long postId;
    private String title;
    private String content;
    private String writer;
    private String filePath;
    private String imageUrl;
    private Date createDate;


    // Entity -> DTO
    public static PostingResponseDto fromEntity(Posting posting) {
        return PostingResponseDto.builder()
                .postId(posting.getId())
                .title(posting.getTitle())
                .content(posting.getContent())
                .writer(posting.getNickName())
                .filePath(posting.getFilePath())
                .createDate(posting.getCreateDate())
                .build();
    }
}
