package com.team.ourblog.dto.response.admin;

import com.team.ourblog.entity.Member;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {

    private Long memberId;
    private String email;
    private String name;
    private String nickname;

    public static UserResponseDto fromEntity(Member member){
        return UserResponseDto.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .nickname(member.getNickname())
                .build();
    }
}
