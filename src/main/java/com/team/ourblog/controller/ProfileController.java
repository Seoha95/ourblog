package com.team.ourblog.controller;

import com.team.ourblog.config.SecurityUtil;
import com.team.ourblog.dto.request.profile.EmailRequestDto;
import com.team.ourblog.dto.request.profile.ImageRequestDto;
import com.team.ourblog.dto.request.profile.NicknameRequestDto;
import com.team.ourblog.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    final private ProfileService profileService;

    // 프로필 이미지 수정
    @PatchMapping("/imageUpdate")
    public ResponseEntity<String> updateImage(@RequestBody ImageRequestDto requestDto){
        Long memberId = SecurityUtil.getCurrentMemberId();
        try {
            profileService.uploadImage(requestDto, memberId);
            return ResponseEntity.status(HttpStatus.OK).body("update success");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("update fail");
        }
    }
    // 닉네임 수정
    @PatchMapping("/nicknameUpdate")
    public ResponseEntity<String> updateNickname(@RequestBody NicknameRequestDto requestDto){
        Long memberId = SecurityUtil.getCurrentMemberId();
        profileService.updateNickname(requestDto, memberId);
        return ResponseEntity.status(HttpStatus.OK).body("update success");

    }

    // 이메일 수정
    @PatchMapping("/emailUpdate")
    public ResponseEntity<String> updateEmail(@RequestBody EmailRequestDto requestDto){
        Long memberId = SecurityUtil.getCurrentMemberId();
        profileService.updateEmail(requestDto, memberId);
        return ResponseEntity.status(HttpStatus.OK).body("update success");

    }


}
