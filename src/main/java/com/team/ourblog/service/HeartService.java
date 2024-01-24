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

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class HeartService {

    private final HeartRepository heartRepository;
    private final MemberRepository memberRepository;
    private final PostingRepository postingRepository;

    // 좋아요
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

    // 좋아요 취소
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
    // 좋아요 조회
    public HashMap<String, Object> getHeart(Long postId, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member Id", String.valueOf(memberId))
        );
        Posting posting = postingRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Posting", "Posting Id", String.valueOf(postId))
        );
        Heart heart = heartRepository.findByPostingIdAndMemberId(postId, memberId);

        List<Heart> heartCount = heartRepository.findByPostingId(postId);
        Integer count = heartCount.size();
        HashMap<String,Object> hashMap = new HashMap<>();

        if(heart ==null){
            hashMap.put("check", false);
            hashMap.put("heartCount", count);
            return hashMap;
        }else{
            hashMap.put("check", true);
            hashMap.put("heartCount", count);
            return hashMap;
        }
    }
}
