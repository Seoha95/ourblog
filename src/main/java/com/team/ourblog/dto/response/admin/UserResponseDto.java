package com.team.ourblog.dto.response.admin;

import com.team.ourblog.dto.response.member.MemberResponseDto;
import com.team.ourblog.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {

    private String email;
    private String name;
    private String nickname;

    public static UserResponseDto fromEntity(Member member){
        return UserResponseDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .nickname(member.getNickname())
                .build();
    }
}
