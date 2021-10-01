package com.market.simple.yuus_market.domains.auth.application.dto;

import lombok.Getter;

@Getter
public class TokenRequestDto {
    private String accessToken;
    private String refreshToken;
}
