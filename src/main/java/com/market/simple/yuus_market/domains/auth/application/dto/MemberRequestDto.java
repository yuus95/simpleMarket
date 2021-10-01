package com.market.simple.yuus_market.domains.auth.application.dto;


import com.market.simple.yuus_market.domains.member.domain.AuthProvider;
import com.market.simple.yuus_market.domains.member.domain.Authority;
import com.market.simple.yuus_market.domains.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {


    @NotBlank(message = "닉네임은 필수 입니다.")
    private String nickname;


    @NotBlank(message = "아이디는 필수입니다.")
    private String userId;
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @NotBlank(message = "핸드폰번호는 필수입니다.")
    @Pattern(regexp ="^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$",message = "xxx-xxxx-xxxx 형식을 맞춰주세요")
    private String phone;




    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .nickname(nickname)
                .userId(userId)
                .password(passwordEncoder.encode(password))
                .phone(phone)
                .authority(Authority.ROLE_USER)
                .provider(AuthProvider.local)
                .build();
    }
}