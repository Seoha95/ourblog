package com.team.ourblog.repository;

import com.team.ourblog.entity.Heart;
import com.team.ourblog.entity.Member;
import com.team.ourblog.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {

    List<Heart> deleteByMemberAndPosting(Member member, Posting posting);

                Heart findByPostingIdAndMemberId(Long POST_ID, Long MEMBER_ID);
}
