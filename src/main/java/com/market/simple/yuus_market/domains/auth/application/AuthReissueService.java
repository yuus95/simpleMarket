package com.market.simple.yuus_market.domains.auth.application;

import com.market.simple.yuus_market.common.handler.ex.CustomException;
import com.market.simple.yuus_market.common.jwt.TokenProvider;
import com.market.simple.yuus_market.domains.auth.application.dto.TokenDto;
import com.market.simple.yuus_market.domains.auth.application.dto.TokenRequestDto;
import com.market.simple.yuus_market.domains.auth.domain.AuthToken;
import com.market.simple.yuus_market.domains.auth.domain.AuthTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.market.simple.yuus_market.common.handler.ex.ErrorCode.*;
@Service
@RequiredArgsConstructor

public class AuthReissueService {
    private final TokenProvider tokenProvider;
    private final AuthTokenRepository authTokenRepository;

    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new CustomException(INVALID_REFRESH_TOKEN);
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        AuthToken refreshToken = (AuthToken) authTokenRepository.findByIdKey(authentication.getName())
                .orElseThrow(() -> new CustomException(REFRESH_TOKEN_NOT_FOUND));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new CustomException(MISMATCH_REFRESH_TOKEN);
        }

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        AuthToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        authTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }
}
