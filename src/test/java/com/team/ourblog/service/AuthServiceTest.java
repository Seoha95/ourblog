package com.team.ourblog.service;

import com.team.ourblog.dto.TokenDto;
import com.team.ourblog.dto.request.TokenRequestDto;
import com.team.ourblog.dto.request.member.MemberRequestDto;
import com.team.ourblog.dto.response.member.MemberResponseDto;
import com.team.ourblog.entity.Image;
import com.team.ourblog.entity.Member;
import com.team.ourblog.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
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
    @Autowired MemberService memberService;
    @Autowired PasswordEncoder passwordEncoder;
    @Autowired MemberRepository memberRepository;
    @Autowired ProfileService profileService;

    private MemberRequestDto dto;

    private MemberRequestDto createTestMemberRequestDto(){
        MemberRequestDto dto = new MemberRequestDto();
        dto.setName("테스트");
        dto.setEmail("test2@naver.com");
        dto.setNickname("테스트야");
        dto.setPassword("dltjgk19950322@");
        return dto;
    }

    @BeforeEach
    void setUp(){
        dto = createTestMemberRequestDto();
    }

    @Test
    void join() {
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
    void createImageStorage(){

        Member member = dto.toMember(passwordEncoder);
        Member saveMember = memberRepository.save(member);

        //when
        Image createdImage = profileService.createImageStorage(saveMember);

        //then
        assertThat(saveMember).isNotNull();
        assertThat(createdImage.getMember()).isEqualTo(saveMember);
        assertThat(createdImage.getUrl()).isNotNull();
        assertThat(createdImage.getUrl()).startsWith("profileImages/");
        assertThat(createdImage.getUrl()).endsWith(".png");
    }


    @Test
    void createDefaultCategoriesOnJoin(){

        //when
        Member member = dto.toMember(passwordEncoder);
        Member saveMember = memberRepository.save(member);

        memberService.createDefaultCategoriesOnJoin(saveMember);

        assertThat(saveMember).isNotNull();
        assertThat(saveMember.getCategories()).isNotNull();
        assertThat(saveMember.getCategories().size()).isEqualTo(4);
    }

    @Test
    void login() {
        authService.join(dto);
        MemberRequestDto requestDto = new MemberRequestDto();
        requestDto.setEmail(dto.getEmail());
        requestDto.setPassword(dto.getPassword());
        TokenDto  tokenDto = authService.login(requestDto);
        assertThat(tokenDto.getAccessToken()).isNotNull();
    }



    @Test
    void reissue() {
        //given
        authService.join(dto);
        //when
        MemberRequestDto requestDto = new MemberRequestDto();
        requestDto.setEmail(dto.getEmail());
        requestDto.setPassword(dto.getPassword());
        TokenDto tokenDto = authService.login(requestDto);

        TokenRequestDto tokenRequestDto = new TokenRequestDto();
        tokenRequestDto.setAccessToken(tokenDto.getAccessToken());
        tokenRequestDto.setRefreshToken(tokenDto.getRefreshToken());

        TokenDto newToken = authService.reissue(tokenRequestDto);

        //then
        assertThat(newToken).isNotNull();
        assertThat(newToken.getAccessToken()).isNotNull();
        assertThat(newToken.getRefreshToken()).isNotNull();

    }

    @Test
    void withdraw() {
    }
}