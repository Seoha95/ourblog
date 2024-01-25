package com.team.ourblog.service;

import com.team.ourblog.common.ResourceNotFoundException;
import com.team.ourblog.dto.request.comment.CommentRequestDto;
import com.team.ourblog.dto.response.comment.CommentResponseDto;
import com.team.ourblog.dto.response.comment.CommentUpdateDto;
import com.team.ourblog.entity.Comment;
import com.team.ourblog.entity.Member;
import com.team.ourblog.entity.Posting;
import com.team.ourblog.repository.CommentRepository;
import com.team.ourblog.repository.MemberRepository;
import com.team.ourblog.repository.PostingRepository;
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
    private  final PostingRepository postingRepository;
    private final MemberRepository memberRepository;

    // 댓글
    public List<CommentResponseDto> getAllComments(Long postId) {
        List<Comment> commentList = commentRepository.findAllWithMemberAndPostingOrderByCreatedDateDesc(postId);
        return commentList.stream()
                .map(CommentResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    //댓글작성
    public CommentResponseDto create(Long postId, Member member, CommentRequestDto requestDto) {
        // posting id 검색
        Posting posting_number = postingRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Posting", "Posting id", String.valueOf(postId))
        );
        // member에서 댓글 작성자 검색
        Member author = memberRepository.findByNickname(member.getNickname()).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member Nickname", String.valueOf(member.getNickname()))
        );

        Comment comment = CommentRequestDto.ofEntity(requestDto);
        comment.setPosting(posting_number);
        comment.setMember(author);

        Comment saveComment = commentRepository.save(comment);
        return CommentResponseDto.fromEntity(saveComment);
    }

    // 댓글 삭제
    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public CommentUpdateDto update(Long commentId, CommentRequestDto requestDto) {
        Comment comment =  commentRepository.findByIdWithMemberAndPosting(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "Comment Id", String.valueOf(commentId))
        );
        comment.update(requestDto.getReply());
        return CommentUpdateDto.fromEntity(comment);
    }
}
