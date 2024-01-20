package com.team.ourblog.controller;

import com.team.ourblog.config.SecurityUtil;
import com.team.ourblog.dto.request.image.ImageRequestDto;
import com.team.ourblog.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    final private ImageService imageService;

    // 프로필 이미지 수정
    @PutMapping("/image")
    public ResponseEntity<String> updateImage(@RequestBody ImageRequestDto requestDto){
        Long memberId = SecurityUtil.getCurrentMemberId();
        try {
            imageService.uploadImage(requestDto, memberId);
            return ResponseEntity.status(HttpStatus.OK).body("update success");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("update fail");
        }
    }


}
