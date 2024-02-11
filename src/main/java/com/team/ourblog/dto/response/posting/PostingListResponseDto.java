package com.team.ourblog.dto.response.posting;

import com.team.ourblog.entity.Posting;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostingListResponseDto {

    private Long id;
    private String title;
    private String content;
    private String createdDate;
    private String writer;



        // Entity -> DTO
        public static PostingListResponseDto fromEntity(Posting posting) {
            return PostingListResponseDto.builder()
                    .id(posting.getId())
                    .title(posting.getTitle())
                    .content(posting.getContent())
                    .createdDate(posting.getCreatedDate())
                    .writer(posting.getMember().getNickname())
                    .build();
        }
}

