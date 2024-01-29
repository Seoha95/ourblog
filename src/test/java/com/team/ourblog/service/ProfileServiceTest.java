package com.team.ourblog.service;

import com.team.ourblog.dto.request.profile.EmailRequestDto;
import com.team.ourblog.dto.request.profile.ImageRequestDto;
import com.team.ourblog.dto.request.profile.NicknameRequestDto;
import com.team.ourblog.dto.request.profile.PassswordRequestDto;
import com.team.ourblog.entity.Authority;
import com.team.ourblog.entity.Image;
import com.team.ourblog.entity.Member;
import com.team.ourblog.repository.MemberRepository;
import com.team.ourblog.repository.ProfileRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class ProfileServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    AuthService authService;

    @Autowired
    ProfileService profileService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProfileRepository profileRepository;

    private Member testMember;

    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void set() {
        testMember = createMember("test10@naver.com", "dltjgk19950322@", "테스트십", "닉네임십");
    }

    private Member createMember(String email, String password, String userName, String nickname) {
        Member member = new Member();
        member.setEmail(email);
        member.setPassword(password);
        member.setName(userName);
        member.setNickname(nickname);
        member.setAuthority(Authority.ROLE_USER);
        return memberRepository.save(member);

    }

    @Test
    void uploadImage() {
        Image createdImage = profileService.createImageStorage(testMember);
        assertThat(createdImage.getUrl()).isEqualTo("src/asset/anonymous.png");

        ImageRequestDto requestDto = new ImageRequestDto();
        requestDto.setImageUrl("src/profile/img.png");
        profileService.uploadImage(requestDto,testMember.getId());
        Image image = profileRepository.findByMember(testMember);

        assertThat(image.getUrl()).isEqualTo("src/profile/img.png");

    }

    @Test
    void updateNickname() {
        assertThat(testMember.getNickname()).isEqualTo("닉네임십");

        NicknameRequestDto requestDto = new NicknameRequestDto();
        requestDto.setNickname("새로운 닉네임");
        profileService.updateNickname(requestDto,testMember.getId());

        assertThat(testMember.getNickname()).isEqualTo("새로운 닉네임");
    }

    @Test
    void updateEmail() {
        assertThat(testMember.getEmail()).isEqualTo("test10@naver.com");

        EmailRequestDto requestDto = new EmailRequestDto();
        requestDto.setEmail("test11@naver.com");
        profileService.updateEmail(requestDto, testMember.getId());

        assertThat(testMember.getEmail()).isEqualTo("test11@naver.com");
    }

    @Test
    void updatePassword() {
        assertThat(testMember.getPassword()).isEqualTo("dltjgk19950322@");

        PassswordRequestDto requestDto = new PassswordRequestDto();
        requestDto.setNewPassword("dldmstj19971011#");
        profileService.updatePassword(requestDto, testMember.getId());

        assertThat(testMember.getPassword()).isNotEqualTo("dltjgk19950322@");
    }

}