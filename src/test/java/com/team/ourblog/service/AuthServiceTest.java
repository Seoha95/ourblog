package com.team.ourblog.service;

import com.team.ourblog.dto.request.member.MemberRequestDto;
import com.team.ourblog.dto.response.member.MemberResponseDto;
import com.team.ourblog.entity.Member;
import com.team.ourblog.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@Transactional
class AuthServiceTest {
    @Autowired AuthService authService;
    @Autowired CustomUserDetailsService customUserDetailsService;
    @Autowired PasswordEncoder passwordEncoder;
    @Autowired MemberRepository memberRepository;
    @Autowired ProfileService profileService;

    @Test
    void join() {
        //given
        MemberRequestDto dto = new MemberRequestDto();
        dto.setName("테스트");
        dto.setEmail("test2@naver.com");
        dto.setNickname("테스트야");
        dto.setPassword("dltjgk19950322@");
        //when
        MemberResponseDto responseDto = authService.join(dto);

        //then
        UserDetails  userDetails = customUserDetailsService.loadUserByUsername(responseDto.getEmail());
        Long memberId = Long.parseLong(userDetails.getUsername());

        Member saveMember = memberRepository.findById(memberId).orElse(null);
        assertThat(saveMember).isNotNull();
        assertThat(saveMember.getEmail()).isEqualTo(dto.getEmail());
    }


    @Test
    void login() {

    }

    @Test
    void reissue() {
    }

    @Test
    void withdraw() {
    }
}