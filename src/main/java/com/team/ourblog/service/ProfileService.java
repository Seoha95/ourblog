package com.team.ourblog.service;

import com.team.ourblog.common.InvalidPasswordException;
import com.team.ourblog.common.ResourceNotFoundException;
import com.team.ourblog.dto.request.profile.EmailRequestDto;
import com.team.ourblog.dto.request.profile.ImageRequestDto;
import com.team.ourblog.dto.request.profile.NicknameRequestDto;
import com.team.ourblog.entity.Image;
import com.team.ourblog.entity.Member;
import com.team.ourblog.repository.MemberRepository;
import com.team.ourblog.repository.ProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileService {


    private final ProfileRepository profileRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    // 회원가입시 기본으로 프로필 생성

    public Image createImageStorage(Member member) {
        String imageUrl = "src/asset/anonymous.png";
        Image image = Image.builder()
                .url(imageUrl)
                .member(member)
                .build();
        return profileRepository.save(image);
    }
    // 프로필 이미지 업데이트
    public void uploadImage(ImageRequestDto requestDto, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member Id", String.valueOf(memberId))
        );

        Image image = profileRepository.findByMember(member);
        image.updateUrl(requestDto.getImageUrl());
        profileRepository.save(image);
    }
    // 닉네임 수정
    public void updateNickname(NicknameRequestDto requestDto, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member Id", String.valueOf(memberId))
        );
        String nickname = requestDto.getNickname();
        member.updateNickname(nickname);
        memberRepository.save(member);
    }

    // 이메일 수정
    public void updateEmail(EmailRequestDto requestDto, Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member Id", String.valueOf(memberId))
        );
        String email = requestDto.getEmail();
        member.updateEmail(email);
        memberRepository.save(member);
    }
    // 비밀번호 수정
    public void updatePassword(Long memberId, String currentPassword, String newPassword) {
        Member member = validatePassword(memberId, currentPassword);
        member.updatePassword(newPassword, passwordEncoder);
    }

    // 현재 비밀번호가 일치하는지 검증
    private Member validatePassword(Long memberId, String currentPassword) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member Id", String.valueOf(memberId))
        );
        if(!passwordEncoder.matches(currentPassword, member.getPassword())){
            throw new InvalidPasswordException("Current password does not match");
        }
        return member;
    }
}
