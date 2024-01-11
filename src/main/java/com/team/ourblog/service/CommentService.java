package com.team.ourblog.service;

import com.team.ourblog.dto.response.comment.CommentResponseDto;
import com.team.ourblog.entity.Comment;
import com.team.ourblog.repository.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    // 댓글
    public List<CommentResponseDto> getAllComments(Long postId) {
        List<Comment> commentList = commentRepository.findAllWithMemberAndPostingOrderByCreateDateDesc(postId);
        return commentList.stream()
                .map(CommentResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
