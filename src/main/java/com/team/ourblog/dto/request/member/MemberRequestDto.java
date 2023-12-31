package com.team.ourblog.dto.request.member;

import com.team.ourblog.entity.Authority;
import com.team.ourblog.entity.Member;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class MemberRequestDto {

    private String email;
    private String nickname;
    private String password;
    private String name;

    public Member toMember(PasswordEncoder passwordEncoder){
        return Member.builder()
                .email(email)
                .nickname(nickname)
                .password(passwordEncoder.encode(password))
                .authority(Authority.ROLE_USER)
                .name(name)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
