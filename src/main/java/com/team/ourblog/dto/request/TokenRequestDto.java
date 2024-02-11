package com.team.ourblog.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TokenRequestDto {

    @NotNull
    String accessToken;
    @NotNull
    String refreshToken;

    @Builder
    public TokenRequestDto(String accessToken, String refreshToken){
        this.accessToken = accessToken;
        this. refreshToken = refreshToken;
    }
}
