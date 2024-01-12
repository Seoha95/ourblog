package com.team.ourblog.repository;

import com.team.ourblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "SELECT c FROM Comment c JOIN FETCH c.member JOIN FETCH c.posting b WHERE b.id = :postId")
    List<Comment> findAllWithMemberAndPostingOrderByCreateDateDesc(Long postId);

    @Query(value = "SELECT c FROM Comment c JOIN FETCH c.member m JOIN FETCH c.posting p WHERE c.id = :commentId")
    Optional<Comment> findByIdWithMemberAndPosting(Long commentId);
}
