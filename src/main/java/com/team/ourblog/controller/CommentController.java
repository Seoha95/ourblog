package com.team.ourblog.controller;

import com.team.ourblog.dto.request.comment.CommentRequestDto;
import com.team.ourblog.dto.response.comment.CommentResponseDto;
import com.team.ourblog.entity.Member;
import com.team.ourblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
            @AuthenticationPrincipal Member member,
            @PathVariable Long postId,
            @RequestBody CommentRequestDto requestDto){

        CommentResponseDto responseDto = commentService.create(postId, member, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

}
