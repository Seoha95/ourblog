package com.team.ourblog.dto.response.member;

import com.team.ourblog.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class MemberPageResponseDto {

    private Long memberId;
    private String email;
    private String nickname;
    private String imageUrl;


    public static MemberPageResponseDto fromEntity(Member member, String imageUrl){
        return MemberPageResponseDto.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .imageUrl(imageUrl)
                .build();

    }
}
