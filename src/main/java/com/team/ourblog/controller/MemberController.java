package com.team.ourblog.controller;

import com.team.ourblog.config.SecurityUtil;
import com.team.ourblog.dto.TokenDto;
import com.team.ourblog.dto.request.TokenRequestDto;
import com.team.ourblog.dto.request.member.MemberRequestDto;
import com.team.ourblog.dto.response.member.MemberInfoResponseDto;
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

    // 이메일 중복체크
    @GetMapping("/checkEmail")
    public ResponseEntity<MemberResponseDto> findMemberInfoByEmail(@RequestParam String email) {
        memberService.findMemberInfoByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 닉네임 중복체크
    @GetMapping("/checkNickname")
    public ResponseEntity<MemberResponseDto> findMemberInfoByNickname(@RequestParam String nickname) {
        memberService.findMemberInfoByNickname(nickname);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<MemberResponseDto> join(@RequestBody MemberRequestDto requestDto) {
        return ResponseEntity.ok(authService.join(requestDto));
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto requestDto, HttpServletResponse response) {
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

    @GetMapping("/info")
    public ResponseEntity<MemberInfoResponseDto> userInfo(){
        Long memberId = SecurityUtil.getCurrentMemberId();
        MemberInfoResponseDto responseDto = memberService.findByIdWithCategoriesAndNickname(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    // 토큰 만료시 재발급
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }



    }



