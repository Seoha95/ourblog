package com.team.ourblog.service;

import com.team.ourblog.common.ResourceNotFoundException;
import com.team.ourblog.dto.request.image.ImageRequestDto;
import com.team.ourblog.entity.Image;
import com.team.ourblog.entity.Member;
import com.team.ourblog.repository.ImageRepository;
import com.team.ourblog.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {


    private final ImageRepository imageRepository;
    private final MemberRepository memberRepository;

    @Value("${file.path}")
    private String uploadFolder;

    // 회원가입시 기본으로 프로필 생성
    public Image createImageStorige(String imageUrl, Member member) {
        Image image = Image.builder()
                .url(imageUrl)
                .member(member)
                .build();
        return imageRepository.save(image);
    }
    // 프로필 이미지 업데이트
    public void uploadImage(ImageRequestDto requestDto, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new ResourceNotFoundException("Member", "Member Id", String.valueOf(memberId))
        );
        MultipartFile file = requestDto.getFile();

        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid + "_" + file.getOriginalFilename();

        File destinationFile = new File(uploadFolder + imageFileName);

        try{
            file.transferTo(destinationFile);

            Image image = imageRepository.findByMember(member);
                // 이미지가 이미 존재하면 url 업데이트
                image.updateUrl("/profileImages/" + imageFileName);

            imageRepository.save(image);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
