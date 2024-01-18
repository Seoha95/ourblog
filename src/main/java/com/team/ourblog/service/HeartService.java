package com.team.ourblog.service;

import com.team.ourblog.common.ResourceNotFoundException;
import com.team.ourblog.dto.response.Heart.HeartResponseDto;
import com.team.ourblog.entity.Heart;
import com.team.ourblog.entity.Member;
import com.team.ourblog.entity.Posting;
import com.team.ourblog.repository.HeartRepository;
import com.team.ourblog.repository.MemberRepository;
import com.team.ourblog.repository.PostingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class HeartService {

    private final HeartRepository heartRepository;
    private final MemberRepository memberRepository;
    private final PostingRepository postingRepository;
    public HeartResponseDto HeartPosting(Long postId, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member Id", String.valueOf(memberId))
        );
        Posting posting = postingRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Posting", "Posting Id", String.valueOf(postId))
        );
        HeartResponseDto responseDto = new HeartResponseDto();
        if(member.getHearts().stream().anyMatch(heart -> heart.getPosting().equals(posting))){
            heartRepository.deleteByMemberAndPosting(member,posting);
            responseDto.setMessage("좋아요 취소");
        }else{
            heartRepository.save(Heart.builder().posting(posting).member(member).build());
            responseDto.setMessage("좋아요 성공");
        }
        return responseDto;
    }
}
