package com.team.ourblog.repository;

import com.team.ourblog.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostingRepository extends JpaRepository<Posting,Long> {

    // 게시물 전체 조회 (로그인 안 했을 때 메인 페이지)
    List<Posting> findAllByOrderByCreateDateDesc();
    // 게시물 제목 또는 내용 또는 닉네임으로 검색하기
    List<Posting> findByTitleContainingOrContentContainingOrNickNameContainingOrderByCreateDateDesc(String searchText);

    // 게시물 상세조회
    @Query(value = "SELECT p FROM Posting p JOIN FETCH p.member")
    Optional<Posting> findByIdWithMemberAndComment(Long postId);
}
