package com.team.ourblog.dto.response.posting;


import com.team.ourblog.entity.Posting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailResponseDto {

    private Long postId;
    private String title;
    private String content;
    private String writer;
    private String createdDate;


    // Entity -> DTO
    public static DetailResponseDto fromEntity(Posting posting){
        return DetailResponseDto.builder()
                .postId(posting.getId())
                .title(posting.getTitle())
                .content(posting.getContent())
                .writer(posting.getMember().getUsername())
                .createdDate(posting.getCreatedDate())
                .build();
    }

}
