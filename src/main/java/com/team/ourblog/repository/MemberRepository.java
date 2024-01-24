package com.team.ourblog.repository;

import com.team.ourblog.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query(value = "SELECT m FROM Member m LEFT JOIN FETCH m.categories WHERE m.id = :memberId")
    Optional<Member> findByIdWithCategoriesAndNickname(Long memberId);
    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickname(String nickname);
    Optional<String> findNicknameById(Long memberId);
}
