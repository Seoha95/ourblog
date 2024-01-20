package com.team.ourblog.repository;

import com.team.ourblog.entity.Image;
import com.team.ourblog.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {

    Image findByMember(Member member);
}
