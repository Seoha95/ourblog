package com.team.ourblog.service;

import com.team.ourblog.dto.request.category.CategoryRequestDto;
import com.team.ourblog.dto.response.category.CategoryResponseDto;
import com.team.ourblog.entity.Authority;
import com.team.ourblog.entity.Member;
import com.team.ourblog.repository.CategoryRepository;
import com.team.ourblog.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class CategoryServiceTest {

    @Autowired CategoryService categoryService;
    @Autowired CategoryRepository categoryRepository;
    @Autowired MemberRepository memberRepository;
    private Long testMeberId;


    @BeforeEach
    public void setup() {
        Member testMember = new Member();
        testMember.setEmail("test");
        testMember.setPassword("dltjgk19950322@");
        testMember.setName("테스트");
        testMember.setNickname("닉네임");
        testMember.setAuthority(Authority.ROLE_USER);

        testMember = memberRepository.save(testMember);
        testMeberId = testMember.getId();

    }

    @Test
    void createCategory() {
        //given
        CategoryRequestDto dto = new CategoryRequestDto();
        dto.setCategoryName("테스트카테고리");

        //when
        CategoryResponseDto responseDto = categoryService.createCategory(dto,testMeberId);

        //then
        assertThat(responseDto).isNotNull();
        assertThat(responseDto.getCategoryName()).isEqualTo(dto.getCategoryName());

    }
    @Test
    void updateCategory() {
        //given
        CategoryRequestDto dto = new CategoryRequestDto();
        dto.setCategoryName("테스트카테고리");

        CategoryResponseDto responseDto =  categoryService.createCategory(dto, testMeberId);


        CategoryRequestDto newDto = new CategoryRequestDto();
        newDto.setCategoryName("업데이트카테고리");

        //when
        CategoryResponseDto newResponse = categoryService.updateCategory(newDto,responseDto.getCategoryId());

        //then
        assertThat(newResponse.getCategoryName()).isEqualTo("업데이트카테고리");
    }

    @Test
    void deleteCategory() {
    }
}