package com.team.ourblog.service;

import com.team.ourblog.common.ResourceNotFoundException;
import com.team.ourblog.dto.request.posting.PostingRequestDto;
import com.team.ourblog.dto.request.posting.PostingUpdateDto;
import com.team.ourblog.dto.response.posting.DetailResponseDto;
import com.team.ourblog.dto.response.posting.PostingResponseDto;
import com.team.ourblog.dto.response.posting.PostingListResponseDto;
import com.team.ourblog.entity.Member;
import com.team.ourblog.entity.Posting;
import com.team.ourblog.repository.MemberRepository;
import com.team.ourblog.repository.PostingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class PostingService {
    private final PostingRepository postingRepository;
    private final MemberRepository memberRepository;

    // 게시물 전체 목록보기
    public List<PostingListResponseDto> getPostingList(){
        List<Posting> postingList = postingRepository.findAllByOrderByCreateDateDesc();
        return postingList.stream()
                .map(PostingListResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
    // 게시물 제목 또는 내용 또는 닉네임으로 검색하기
    public List<PostingListResponseDto> getPostingList(String searchText){
        List<Posting> postingList = postingRepository.findByTitleContainingOrContentContainingOrNickNameContainingOrderByCreateDateDesc(searchText);
        return postingList.stream()
                .map(PostingListResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    // 게시물 작성하기
    public PostingResponseDto createPosting(PostingRequestDto requestDto, Member member){

        Posting posting = PostingRequestDto.ofEntity(requestDto);
        Member nickName = memberRepository.findByNickname(member.getUsername()).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member nickname", member.getUsername())
        );
        posting.setMappingMember(nickName);
        Posting savePosting =  postingRepository.save(posting);

        return PostingResponseDto.fromEntity(savePosting, member.getUsername());
    }

    // 게시물 상세보기
    public DetailResponseDto getPostingDetail(Long postId) {
        Posting postingDetail = postingRepository.findByIdWithMemberAndComment(postId).orElseThrow(
                () -> new ResourceNotFoundException("Posting", "Post Id",String.valueOf(postId))
        );
        return DetailResponseDto.fromEntity(postingDetail);

    }
    // 게시물 수정
    public DetailResponseDto update(Long postId, PostingUpdateDto requestDto) {
        Posting updatePosting = postingRepository.findByIdWithMemberAndComment(postId).orElseThrow(
                () -> new ResourceNotFoundException("Posting", "Post Id",String.valueOf(postId))
        );
        updatePosting.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getFilePath(), requestDto.getImageUrl());
        return DetailResponseDto.fromEntity(updatePosting);
    }

    // 게시물 삭제
    public void delete(Long postId) {
        postingRepository.deleteById(postId);
    }
}
