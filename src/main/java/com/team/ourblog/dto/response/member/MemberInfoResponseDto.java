package com.team.ourblog.dto.response.member;

import com.team.ourblog.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Builder
@AllArgsConstructor
@Getter
@Setter
public class MemberInfoResponseDto {

    //private String nickname;
    private List<Category> categories;

    public static MemberInfoResponseDto fromEntity(List<Category> categories) {
        return MemberInfoResponseDto.builder()
                .categories(categories)
                .build();
    }
}
