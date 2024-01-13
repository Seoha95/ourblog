package com.team.ourblog.dto.response.member;

import com.team.ourblog.entity.Category;
import lombok.*;

import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberInfoResponseDto {

    private String nickname;
    private List<Category> categories;




    public static MemberInfoResponseDto fromEntity(List<Category> categories, String nickname) {
        return MemberInfoResponseDto.builder()
                .categories(categories)
                .nickname(nickname)
                .build();
    }
}