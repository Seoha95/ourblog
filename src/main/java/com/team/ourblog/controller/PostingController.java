package com.team.ourblog.controller;

import com.team.ourblog.config.SecurityUtil;
import com.team.ourblog.dto.request.posting.PostingRequestDto;
import com.team.ourblog.dto.request.posting.PostingUpdateDto;
import com.team.ourblog.dto.response.posting.DetailResponseDto;
import com.team.ourblog.dto.response.posting.PostingListResponseDto;
import com.team.ourblog.dto.response.posting.PostingResponseDto;
import com.team.ourblog.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posting")
@RequiredArgsConstructor
public class PostingController {

    private final PostingService postingService;
    private final String commonPath = "\\images";

    // 게시물 전체 목록 보기 (로그인 안 한 상태의 메인페이지)
    @GetMapping("/list")
    public ResponseEntity<List<PostingListResponseDto>> getPostingList(@RequestParam String searchText){
        List<PostingListResponseDto> postingList;

        if(searchText == null) {
            postingList = postingService.getPostingList(); // 게시물 전체 조회
        }else {
            postingList = postingService.getPostingList(searchText); // 게시물 검색
        }
        return ResponseEntity.status(HttpStatus.OK).body(postingList);
    }

    // 게시물 작성
    @PostMapping("/create")
    public ResponseEntity<PostingResponseDto> createPosting(@RequestBody PostingRequestDto requestDto) {
            Long memberId = SecurityUtil.getCurrentMemberId();
            PostingResponseDto responseDto = postingService.createPosting(requestDto, memberId);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 게시물 상세보기
    @GetMapping("/{postId}")
    public ResponseEntity<DetailResponseDto> getPostingDetail(@PathVariable Long postId){
        DetailResponseDto postingDetail = postingService.getPostingDetail(postId);

        return ResponseEntity.status(HttpStatus.OK).body(postingDetail);
    }

    // 게시물 수정
    @PutMapping("/{postId}")
    public ResponseEntity<DetailResponseDto> updatePosting(@PathVariable Long postId, @RequestBody PostingUpdateDto requestDto){

        DetailResponseDto updateDto = postingService.update(postId, requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }

    // 게시물 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<Long> deletePosting(@PathVariable Long postId){
        postingService.delete(postId);
        return ResponseEntity.status(HttpStatus.OK).build();

    }



}







