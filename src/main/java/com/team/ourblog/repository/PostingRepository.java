package com.team.ourblog.repository;

import com.team.ourblog.entity.Member;
import com.team.ourblog.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostingRepository extends JpaRepository<Posting,Long> {

    // 게시물 전체 조회 (로그인 안 했을 때 메인 페이지)
    List<Posting> findAllByOrderByCreatedDateDesc();
    // 게시물 제목 또는 내용 또는 닉네임으로 검색하기
    List<Posting> findByTitleContainingOrContentContainingOrNickNameContainingOrderByCreatedDateDesc(String title, String content, String nickname);
    // 카테고리별로 게시물 조회
    List<Posting> findAllByCategory_Id(Long categoryId);

    // 게시물 상세조회
    @Query(value = "SELECT p FROM Posting p JOIN FETCH p.member")
    Optional<Posting> findByIdWithMemberAndComment(Long postId);

    // 맴버의 게시물 전체 삭제
    void deleteAllByMember(Member member);

    // 로그인한 사용자의 게시물 전체 조회
    List<Posting> findAllByMember_Id(Long memberId);




}
