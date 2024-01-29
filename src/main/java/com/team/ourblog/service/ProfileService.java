package com.team.ourblog.service;

import com.team.ourblog.common.MemberException;
import com.team.ourblog.common.ResourceNotFoundException;
import com.team.ourblog.dto.request.profile.EmailRequestDto;
import com.team.ourblog.dto.request.profile.ImageRequestDto;
import com.team.ourblog.dto.request.profile.NicknameRequestDto;
import com.team.ourblog.dto.request.profile.PassswordRequestDto;
import com.team.ourblog.entity.Image;
import com.team.ourblog.entity.Member;
import com.team.ourblog.repository.MemberRepository;
import com.team.ourblog.repository.ProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public void updatePassword(PassswordRequestDto requestDto, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member Id", String.valueOf(memberId))
        );
        String encodePassword = passwordEncoder.encode(requestDto.getNewPassword());
        member.updatePassword(encodePassword);
        memberRepository.save(member);
    }

    // 현재 비밀번호가 일치하는지 검증
    private Member validatePassword(Long memberId, String currentPassword) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member Id", String.valueOf(memberId))
        );

        if(!currentPassword.equals(member.getPassword())){
            throw new MemberException("패스워드 불일치", HttpStatus.BAD_REQUEST);
        }
        return member;
    }
}
