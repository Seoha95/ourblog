package com.team.ourblog.dto.request.profile;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ImageRequestDto {

    private MultipartFile file;

}
