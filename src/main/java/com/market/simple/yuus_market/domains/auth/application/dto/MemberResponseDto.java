package com.market.simple.yuus_market.domains.auth.application.dto;

import com.market.simple.yuus_market.domains.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 저장했을 경우 userID 반환
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private String userId;

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member.getUserId());
    }
}