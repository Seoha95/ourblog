package com.team.ourblog.controller;


import com.team.ourblog.config.SecurityUtil;
import com.team.ourblog.dto.TokenDto;
import com.team.ourblog.dto.request.TokenRequestDto;
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

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto requestDto){
        return ResponseEntity.ok(authService.login(requestDto));
    }
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }

    }



