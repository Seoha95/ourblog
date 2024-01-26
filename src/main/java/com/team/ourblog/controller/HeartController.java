package com.team.ourblog.controller;

import com.team.ourblog.config.SecurityUtil;
import com.team.ourblog.entity.Heart;
import com.team.ourblog.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/heart")
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;
    // 좋아요 갯수 조회
    @GetMapping("/get/{postId}")
    public HashMap<String, Object> getHeart(@PathVariable Long postId) {
        //Long memberId = SecurityUtil.getCurrentMemberId();

        return heartService.getHeart(postId);
    }

    // 좋아요
    @PostMapping("/post/{postId}")
    public ResponseEntity<String> heartInsert(@PathVariable Long postId) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        Heart heart = heartService.heartInsert(postId, memberId);
        if(heart == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미 좋아요 눌렀습니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("좋아요");
    }
    // 좋아요 취소
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> heartDelete(@PathVariable Long postId) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        Heart heart = heartService.heartDelete(postId, memberId);
        if(heart == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("취소할 좋아요가 없습니다.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("좋아요 취소");
    }
}
