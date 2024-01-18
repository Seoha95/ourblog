package com.team.ourblog.controller;

import com.team.ourblog.config.SecurityUtil;
import com.team.ourblog.dto.response.Heart.HeartResponseDto;
import com.team.ourblog.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heart")
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;

    // 좋아요 기능
    @PostMapping("/{postId}")
    public ResponseEntity<HeartResponseDto> HeartPosting(@PathVariable Long postId){
        Long memberId = SecurityUtil.getCurrentMemberId();
        HeartResponseDto responseDto = heartService.HeartPosting(postId,memberId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
