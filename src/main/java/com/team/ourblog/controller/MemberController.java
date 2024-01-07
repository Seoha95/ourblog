package com.team.ourblog.controller;

import com.team.ourblog.dto.TokenDto;
import com.team.ourblog.dto.request.TokenRequestDto;
import com.team.ourblog.dto.request.member.MemberRequestDto;
import com.team.ourblog.dto.response.member.MemberResponseDto;
import com.team.ourblog.service.AuthService;
import com.team.ourblog.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
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
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto requestDto, HttpServletResponse response){
        TokenDto tokenDto = authService.login(requestDto);

        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", tokenDto.getRefreshToken())
        .maxAge(60 * 60 * 24) // 쿠키의 유효 기간을 설정 (초 단위)
        .path("/")
                .secure(true)
                .httpOnly(true)
                .sameSite("None").build();

        // 쿠키를 HTTP 응답 헤더에 추가
        response.setHeader("set-Cookie", refreshTokenCookie.toString());

        return ResponseEntity.ok(tokenDto);
    }
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }

    }



