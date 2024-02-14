package com.team.ourblog.service;

import com.team.ourblog.dto.request.posting.PostingRequestDto;
import com.team.ourblog.dto.response.posting.PostingResponseDto;
import com.team.ourblog.entity.Authority;
import com.team.ourblog.entity.Category;
import com.team.ourblog.entity.Heart;
import com.team.ourblog.entity.Member;
import com.team.ourblog.repository.CategoryRepository;
import com.team.ourblog.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Transactional
class HeartServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PostingService postingService;

    @Autowired
    HeartService heartService;

    @Autowired
    CategoryRepository categoryRepository;

    private PostingResponseDto responseDto;
    private PostingResponseDto responseDto2;
    private Member testMember;
    private Member testMember2;

    @BeforeEach
    void setUp(){
        testMember = createMember(2L,"test10@naver.com", "dltjgk19950322@","테스트","닉네임십");
        memberRepository.save(testMember);

        testMember2 = createMember(3L,"test11@naver.com", "dltjgk19950322@","테스트십일","닉네임십일");
        memberRepository.save(testMember2);

        Category category = createCategory("음식", testMember);
        responseDto = createPosting(String.valueOf(category.getId()),"테스트제목","테스트내용",testMember.getNickname());

        Category category2 = createCategory("취미", testMember2);
        responseDto2 =  createPosting(String.valueOf(category2.getId()),"테스트제목2","테스트내용2",testMember2.getNickname());
    }

    private Member createMember(Long id,String email, String password, String userName, String nickname) {
        Member member =  new Member(id,email,nickname,userName,password,Authority.ROLE_USER);
        return memberRepository.save(member);

    }

    private PostingResponseDto createPosting(String categoryId,String title, String content, String autor) {
        PostingRequestDto requestDto = new PostingRequestDto();
        requestDto.setCategoryId(categoryId);
        requestDto.setTitle(title);
        requestDto.setContent(content);
        requestDto.setNickname(autor);

        return postingService.createPosting(requestDto, testMember.getId());
    }

    private Category createCategory(String categoryName, Member member) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setMember(member);
        return categoryRepository.save(category);
    }

    @Test
    void heartInsert() {

        Heart heart = heartService.heartInsert(responseDto.getPostId(), testMember.getId());
        Heart heart1 = heartService.heartInsert(responseDto2.getPostId(), testMember2.getId());

        assertThat(heart.getId()).isNotNull();
        assertThat(heart1.getId()).isNotNull();

    }

    @Test
    void heartDelete() {

        heartService.heartInsert(responseDto.getPostId(), testMember.getId());
        heartService.heartInsert(responseDto2.getPostId(), testMember2.getId());

        HashMap<String, Object> hashMap =  heartService.getHeartUser(responseDto.getPostId(),testMember2.getId());

        int expectedHeartCount = 1;
        assertEquals(expectedHeartCount, hashMap.get("heartCount"));
       Heart heart = heartService.heartDelete(responseDto2.getPostId(), testMember2.getId());

       // null이 아니면 좋아요 취소
        assertThat(heart).isNotNull();

    }

    @Test
    void getHeart() {

        heartService.heartInsert(responseDto.getPostId(), testMember.getId());
        heartService.heartInsert(responseDto.getPostId(), testMember2.getId());

        HashMap<String, Object> hashMap =  heartService.getHeart(responseDto.getPostId());
        int expectedHeartCount = 2;
        boolean expectedCheck = false;
        assertEquals(expectedHeartCount, hashMap.get("heartCount"));
        assertEquals(expectedCheck, hashMap.get("check"));
    }
}