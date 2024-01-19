package com.team.ourblog.service;

import com.team.ourblog.entity.Image;
import com.team.ourblog.entity.Member;
import com.team.ourblog.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;
    public Image createImageStorige(String imageUrl, Member member) {
        Image image = Image.builder()
                .url(imageUrl)
                .member(member)
                .build();
        return imageRepository.save(image);
    }
}
