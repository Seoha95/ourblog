package com.team.ourblog.service;

import com.team.ourblog.common.ResourceNotFoundException;
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


    public Heart heartInsert(Long postId, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member Id", String.valueOf(memberId))
        );
        Posting posting = postingRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Posting", "Posting Id", String.valueOf(postId))
        );
        // 이미 좋아요 되어 있으면 에러 반환

        Heart heart = heartRepository.findByPostingIdAndMemberId(postId, memberId);
        if(heart == null){
            Heart newHeart = new Heart();
            newHeart.addMember(member);
            newHeart.addPosting(posting);
            heartRepository.save(newHeart);
            return newHeart;
        }else{
            return null;
        }
    }

    public Heart heartDelete(Long postId, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member Id", String.valueOf(memberId))
        );
        Posting posting = postingRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Posting", "Posting Id", String.valueOf(postId))
        );

        Heart heart = heartRepository.findByPostingIdAndMemberId(postId, memberId);
        if(heart == null){
          return null;
        }else{
            heartRepository.deleteById(heart.getId());
            return heart;
        }
    }
}
