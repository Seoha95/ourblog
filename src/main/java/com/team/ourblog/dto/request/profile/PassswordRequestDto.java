package com.team.ourblog.dto.request.profile;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PassswordRequestDto {

    private String currentPassword;

    private String newPassword;


}
