package com.team.ourblog.repository;

import com.team.ourblog.entity.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {

    Heart findByPostingIdAndMemberId(Long POST_ID, Long MEMBER_ID);

    List<Heart> findByPostingId(Long postId);
}
