package com.team.ourblog.controller;

import com.team.ourblog.config.SecurityUtil;
import com.team.ourblog.dto.request.posting.PostingRequestDto;
import com.team.ourblog.dto.request.posting.PostingUpdateDto;
import com.team.ourblog.dto.response.posting.DetailResponseDto;
import com.team.ourblog.dto.response.posting.PostingListResponseDto;
import com.team.ourblog.dto.response.posting.PostingResponseDto;
import com.team.ourblog.service.PostingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posting")
@RequiredArgsConstructor
public class PostingController {

    private final PostingService postingService;


    // 게시물 전체 목록 보기 (로그인 안 한 상태의 메인페이지)
    @GetMapping("/list")
    public ResponseEntity<Page<PostingListResponseDto>> getPostingList(
            @RequestParam String searchText,
            @PageableDefault(size = 8, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable){
        Page<PostingListResponseDto> postingList;

        if(searchText == null) {
            postingList = postingService.getPostingList(pageable); // 게시물 전체 조회
        }else {
            postingList = postingService.getPostingList(searchText, pageable); // 게시물 검색
        }
        return ResponseEntity.status(HttpStatus.OK).body(postingList);
    }

    // 게시물 작성
    @PostMapping("/create")
    public ResponseEntity<PostingResponseDto> createPosting(@RequestBody @Valid PostingRequestDto requestDto) {
            Long memberId = SecurityUtil.getCurrentMemberId();
            PostingResponseDto responseDto = postingService.createPosting(requestDto, memberId);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // 게시물 상세보기
    @GetMapping("/detail/{postId}")
    public ResponseEntity<List<DetailResponseDto>> getPostingDetail(@PathVariable Long postId){
        List<DetailResponseDto> postingDetail = postingService.getPostingDetail(postId);

        return ResponseEntity.status(HttpStatus.OK).body(postingDetail);
    }

     //게시물 수정
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







