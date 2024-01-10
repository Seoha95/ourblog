package com.team.ourblog.controller;

import com.team.ourblog.dto.request.posting.PostingRequestDto;
import com.team.ourblog.dto.request.posting.PostingUpdateDto;
import com.team.ourblog.dto.response.ResponseMsgDto;
import com.team.ourblog.dto.response.posting.DetailResponseDto;
import com.team.ourblog.dto.response.posting.PostingResponseDto;
import com.team.ourblog.dto.response.posting.PostingListResponseDto;
import com.team.ourblog.entity.Member;
import com.team.ourblog.service.PostingService;
import com.team.ourblog.util.MD5Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.List;


@RequestMapping("/posting")
@RequiredArgsConstructor
public class PostingController {

    private final PostingService postingService;
    private final String commonPath = "\\images";

    // 게시물 전체 목록 보기 (로그인 안 한 상태의 메인페이지)
    @GetMapping("/list")
    public ResponseEntity<List<PostingListResponseDto>> getPostingList(){
        List<PostingListResponseDto> postingList = postingService.getPostingList();
        return new ResponseEntity<>(postingList, HttpStatus.OK);
    }

    // 게시물 작성
    @PostMapping("/create")
    public ResponseEntity<?> createPosting(
            @RequestParam(value = "file", required = false) MultipartFile files,
            @RequestBody PostingRequestDto requestDto,
            @AuthenticationPrincipal Member member) {


        try {
            String filename = "basic.jpg";
            if (files != null) {
                String origFillename = files.getOriginalFilename();
                filename = new MD5Generator(origFillename).toString() + ".jpg";

                /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다.*/
                String savePath = System.getProperty("user.dir") + commonPath;

                /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다.*/
                if (!new File(savePath).exists()) {
                    try {
                        new File(savePath).mkdir();
                    } catch (Exception e) {

                        e.getStackTrace();

                    }
                }
                String filePath = savePath + "\\" + filename;
                files.transferTo(new File(filePath));
            }

            requestDto.setFilePath(filename);

            PostingResponseDto responseDto = postingService.createPosting(requestDto, member);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

        } catch (Exception e) {

            ResponseMsgDto errorMsg = new ResponseMsgDto("file upload fail");
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMsg);
        }

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

    @DeleteMapping("/{postId}")
    public ResponseEntity<Long> deletePosting(@PathVariable Long postId){
        postingService.delete(postId);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

}







