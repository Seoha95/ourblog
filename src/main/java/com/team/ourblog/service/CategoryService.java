package com.team.ourblog.service;

import com.team.ourblog.common.ResourceNotFoundException;
import com.team.ourblog.dto.request.category.CategoryRequestDto;
import com.team.ourblog.dto.response.category.CategoryResponseDto;
import com.team.ourblog.entity.Category;
import com.team.ourblog.entity.Member;
import com.team.ourblog.repository.CategoryRepository;
import com.team.ourblog.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    // 카테고리 만들기
    public CategoryResponseDto createCategory(CategoryRequestDto requestDto, Long memberId){

        Member memberInfo = memberRepository.findById(memberId).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member Id", String.valueOf(memberId))
        );
        Category category = CategoryRequestDto.ofEntity(requestDto);
        category.setMember(memberInfo);
        Category saveCategory = categoryRepository.save(category);

        return CategoryResponseDto.fromEntity(saveCategory);
    }

    // 카테고리 이름 수정
    public CategoryResponseDto updateCategory(CategoryRequestDto requestDto, Long categoryId) {

            Category updateCategory = categoryRepository.findById(categoryId).orElseThrow(
                    () -> new ResourceNotFoundException("Category", "Category Id", String.valueOf(categoryId))
            );
            updateCategory.update(requestDto.getCategoryName());
            categoryRepository.save(updateCategory);
            return CategoryResponseDto.fromEntity(updateCategory);
    }

    // 카테고리 삭제
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    // 카테고리 삭제

}
