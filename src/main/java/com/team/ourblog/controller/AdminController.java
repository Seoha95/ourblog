package com.team.ourblog.controller;


import com.team.ourblog.dto.response.admin.UserResponseDto;
import com.team.ourblog.service.AuthService;
import com.team.ourblog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final MemberService memberService;
    private final AuthService authService;

    // 관리자가 전체 회원정보 조회
    @GetMapping("/members")
    public ResponseEntity<List<UserResponseDto>> findAllMember() {
        List<UserResponseDto> userResponseDtoList = memberService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDtoList);
    }

    // 관리자가 회원의 정보를 삭제
    @DeleteMapping("/memberDelete/{memberId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long memberId) {
        //memberService.deleteUser(memberId);
        authService.deleteUser(memberId);

        return ResponseEntity.status(HttpStatus.OK).body("delete success");
    }
}