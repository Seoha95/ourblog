package com.team.ourblog.repository;

import com.team.ourblog.entity.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    Heart findByPostingIdAndMemberId(Long POST_ID, Long MEMBER_ID);

    List<Heart> findByPostingId(Long postId);
}
