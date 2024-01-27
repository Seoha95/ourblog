package com.team.ourblog.service;

import com.team.ourblog.dto.request.posting.PostingRequestDto;
import com.team.ourblog.dto.response.posting.PostingResponseDto;
import com.team.ourblog.entity.Authority;
import com.team.ourblog.entity.Heart;
import com.team.ourblog.entity.Member;
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

    private PostingResponseDto dto;
    private Member testMember;
    private Member testMember2;

    @BeforeEach
    void setUp(){
        testMember = new Member();
        testMember.setEmail("test11@naver.com");
        testMember.setPassword("dltjgk19950322@");
        testMember.setName("테스트일일");
        testMember.setNickname("닉네임일일");
        testMember.setAuthority(Authority.ROLE_USER);

        memberRepository.save(testMember);

        testMember2 = new Member();
        testMember2.setEmail("test12@naver.com");
        testMember2.setPassword("dltjgk19950322@");
        testMember2.setName("테스트이이");
        testMember2.setNickname("닉네임이이");
        testMember2.setAuthority(Authority.ROLE_USER);

        memberRepository.save(testMember2);

        PostingRequestDto requestDto = new PostingRequestDto();
        requestDto.setTitle("테스트제목");
        requestDto.setContent("테스트내용");
        requestDto.setNickName("닉네임일일");
        postingService.createPosting(requestDto, testMember.getId());

        PostingRequestDto requestDto2 = new PostingRequestDto();
        requestDto2.setTitle("테스트제목2");
        requestDto2.setContent("테스트내용2");
        requestDto2.setNickName("닉네임이이");
        dto = postingService.createPosting(requestDto2, testMember2.getId());
    }

    @Test
    void heartInsert() {

        Heart heart = heartService.heartInsert(dto.getPostId(), testMember.getId());
        Heart heart1 = heartService.heartInsert(dto.getPostId(), testMember2.getId());

        assertThat(heart.getId()).isNotNull();
        assertThat(heart1.getId()).isNotNull();

    }

    @Test
    void heartDelete() {

        heartService.heartInsert(dto.getPostId(), testMember.getId());
        heartService.heartInsert(dto.getPostId(), testMember2.getId());

        HashMap<String, Object> hashMap =  heartService.getHeartUser(dto.getPostId(),testMember2.getId());

        int expectedHeartCount = 2;
        assertEquals(expectedHeartCount, hashMap.get("heartCount"));
       Heart heart = heartService.heartDelete(dto.getPostId(), testMember2.getId());

       // null이 아니면 좋아요 취소
        assertThat(heart).isNotNull();

    }

    @Test
    void getHeart() {

        heartService.heartInsert(dto.getPostId(), testMember.getId());
        heartService.heartInsert(dto.getPostId(), testMember2.getId());

        HashMap<String, Object> hashMap =  heartService.getHeart(dto.getPostId());
        int expectedHeartCount = 2;
        boolean expectedCheck = false;
        assertEquals(expectedHeartCount, hashMap.get("heartCount"));
        assertEquals(expectedCheck, hashMap.get("check"));
    }
}