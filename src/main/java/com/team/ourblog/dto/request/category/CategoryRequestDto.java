package com.team.ourblog.dto.request.category;

import com.team.ourblog.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDto {


    private String categoryName;

    public static Category ofEntity(CategoryRequestDto requestDto) {

        return Category.builder()
                .categoryName(requestDto.categoryName)
                .build();
    }
}
