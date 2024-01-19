package com.team.ourblog.repository;

import com.team.ourblog.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image,Long> {
}
