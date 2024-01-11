package com.team.ourblog.controller;

import com.team.ourblog.dto.response.comment.CommentResponseDto;
import com.team.ourblog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
