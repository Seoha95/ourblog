package com.team.ourblog.controller;

import com.team.ourblog.config.SecurityUtil;
import com.team.ourblog.dto.request.category.CategoryRequestDto;
import com.team.ourblog.dto.response.category.CategoryResponseDto;
import com.team.ourblog.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리 만들기
    @PostMapping("/create")
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto requestDto){
        Long memberId = SecurityUtil.getCurrentMemberId();

        CategoryResponseDto responseDto = categoryService.createCategory(requestDto, memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 카테고리 이름 수정
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequestDto requestDto){

        CategoryResponseDto updateDto = categoryService.updateCategory(requestDto, categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }

}
