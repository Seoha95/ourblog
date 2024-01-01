package com.team.ourblog.controller;


import com.team.ourblog.config.SecurityUtil;
import com.team.ourblog.dto.request.member.MemberRequestDto;
import com.team.ourblog.dto.response.member.MemberResponseDto;
import com.team.ourblog.service.AuthService;
import com.team.ourblog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final AuthService authService;
    private final MemberService memberService;

    @GetMapping("/me")
    public ResponseEntity<MemberResponseDto> findMemberInfoById() {
        return ResponseEntity.ok(memberService.findMemberInfoById(SecurityUtil.getCurrentMemberId()));
    }

    @GetMapping("/checkEmail")
    public ResponseEntity<MemberResponseDto> findMemberInfoByEmail(@RequestParam String email) {
            memberService.findMemberInfoByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/checkNickname")
    public ResponseEntity<MemberResponseDto> findMemberInfoByNickname(@RequestParam String nickname) {
        memberService.findMemberInfoByNickname(nickname);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/join")
    public ResponseEntity<MemberResponseDto> join(@RequestBody MemberRequestDto requestDto) {
        return ResponseEntity.ok(authService.join(requestDto));
        }
    }



