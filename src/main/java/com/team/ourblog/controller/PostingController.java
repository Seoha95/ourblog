package com.team.ourblog.controller;

import com.team.ourblog.dto.request.posting.PostingRequestDto;
import com.team.ourblog.dto.response.ResponseMsgDto;
import com.team.ourblog.dto.response.posting.PostingResponseDto;
import com.team.ourblog.dto.response.posting.PostingListResponseDto;
import com.team.ourblog.entity.Member;
import com.team.ourblog.service.PostingService;
import com.team.ourblog.util.MD5Generator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.List;

@Slf4j
@RequestMapping("/posting")
@RequiredArgsConstructor
public class PostingController {

    private final PostingService postingService;
    private final String commonPath = "\\images";

    @GetMapping("/list")
    public ResponseEntity<List<PostingListResponseDto>> getPostingList(){
        List<PostingListResponseDto> postingList = postingService.getPostingList();
        return new ResponseEntity<>(postingList, HttpStatus.OK);
    }

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

}







