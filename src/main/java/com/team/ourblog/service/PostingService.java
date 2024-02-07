package com.team.ourblog.service;

import com.team.ourblog.common.ResourceNotFoundException;
import com.team.ourblog.dto.request.posting.PostingRequestDto;
import com.team.ourblog.dto.request.posting.PostingUpdateDto;
import com.team.ourblog.dto.response.posting.DetailResponseDto;
import com.team.ourblog.dto.response.posting.PostingListResponseDto;
import com.team.ourblog.dto.response.posting.PostingResponseDto;
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
        List<Posting> postingList = postingRepository.findAllByOrderByCreatedDateDesc();
        return postingList.stream()
                .map(PostingListResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    // 로그인한 회원의 게시물 전체보기
    public List<PostingListResponseDto> getUserPostingList(Long memberId){
        List<Posting> postingList = postingRepository.findAllByMember_Id(memberId);
        return postingList.stream()
                .map(PostingListResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
    // 게시물 제목 또는 내용 또는 닉네임으로 검색하기
    public List<PostingListResponseDto> getPostingList(String searchText){
        List<Posting> postingList = postingRepository.findByTitleContainingOrContentContainingOrNickNameContainingOrderByCreatedDateDesc(searchText, searchText, searchText);
        return postingList.stream()
                .map(PostingListResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
    // 카테고리별로 게시물 조회
    public List<PostingListResponseDto> getPostingListCategory(Long categoryId){
        List<Posting> postingList = postingRepository.findAllByCategory_Id(categoryId);
        return postingList.stream()
                .map(PostingListResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    // 게시물 작성하기
    public PostingResponseDto createPosting(PostingRequestDto requestDto, Long memberId){

        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member Id", String.valueOf(memberId))
        );

        Posting posting = PostingRequestDto.ofEntity(requestDto);
        posting.setMappingMember(member);
        Posting savePosting =  postingRepository.save(posting);

        return PostingResponseDto.fromEntity(savePosting);
    }

    // 게시물 상세보기
    public List<DetailResponseDto> getPostingDetail(Long postId) {
        List<Posting> postingDetail = postingRepository.findByIdWithMemberAndComment(postId);
        return postingDetail.stream().map(DetailResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

     //게시물 수정
    public DetailResponseDto update(Long postId, PostingUpdateDto requestDto) {
        Posting posting = postingRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Posting", "Posting Id", String.valueOf(postId))
        );
        posting.update(requestDto.getTitle(), requestDto.getContent());
        return DetailResponseDto.fromEntity(posting);
    }

    // 게시물 삭제
    public void delete(Long postId) {
        postingRepository.deleteById(postId);
    }
}
