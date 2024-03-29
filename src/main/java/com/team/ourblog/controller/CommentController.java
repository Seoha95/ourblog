package com.team.ourblog.controller;

import com.team.ourblog.config.SecurityUtil;
import com.team.ourblog.dto.request.comment.CommentRequestDto;
import com.team.ourblog.dto.response.comment.CommentResponseDto;
import com.team.ourblog.dto.response.comment.CommentUpdateDto;
import com.team.ourblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //댓글
    @GetMapping("/list/{postId}")
    public ResponseEntity<List<CommentResponseDto>> getCommentList(@PathVariable Long postId) {
        List<CommentResponseDto> commentList = commentService.getAllComments(postId);
        return ResponseEntity.status(HttpStatus.OK).body(commentList);
    }
    // 댓글 작성
    @PostMapping("/create/{postId}")
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable Long postId,
            @RequestBody CommentRequestDto requestDto){
        Long memberId = SecurityUtil.getCurrentMemberId();
        CommentResponseDto responseDto = commentService.create(postId, memberId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
    //댓글 수정
    @PutMapping("/update/{commentId}")
    public ResponseEntity<CommentUpdateDto> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto requestDto){

        CommentUpdateDto responseDto = commentService.update(commentId, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
    // 댓글 삭제
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Long> deleteComment(@PathVariable Long commentId){
        commentService.delete(commentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
