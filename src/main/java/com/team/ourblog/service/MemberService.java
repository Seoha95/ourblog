package com.team.ourblog.service;


import com.team.ourblog.common.MemberException;
import com.team.ourblog.dto.response.member.MemberResponseDto;
import com.team.ourblog.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDto findMemberInfoById(Long memberId) {
        return memberRepository.findById(memberId)
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }
    public HttpStatus findMemberInfoByEmail(String email){
        if (memberRepository.findByEmail(email).isPresent()){
            throw new MemberException("이미 사용 중인 이메일입니다.", HttpStatus.BAD_REQUEST);
        }
        return HttpStatus.OK;
    }

    public  HttpStatus findMemberInfoByNickname(String nickname){
        if (memberRepository.findByNickname(nickname).isPresent()){
            throw new MemberException("이미 사용 중인 닉네임입니다.", HttpStatus.BAD_REQUEST);
        }
        return HttpStatus.OK;
    }

}