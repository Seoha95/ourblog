package com.team.ourblog.dto.response.posting;

import com.team.ourblog.entity.Posting;
import lombok.*;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostingListResponseDto {

    private Long id;
    private String title;
    private String content;
    private Date createDate;
    private String writer;
    private Long likeCnt;


        // Entity -> DTO
        public static PostingListResponseDto fromEntity(Posting posting) {
            return PostingListResponseDto.builder()
                    .id(posting.getId())
                    .title(posting.getTitle())
                    .content(posting.getContent())
                    .createDate(posting.getCreateDate())
                    .writer(posting.getMember().getNickname())
                    .likeCnt((long)posting.getHearts().size())
                    .build();
        }
}

