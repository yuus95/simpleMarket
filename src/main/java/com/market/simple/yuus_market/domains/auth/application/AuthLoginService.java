package com.market.simple.yuus_market.domains.auth.application;

import com.market.simple.yuus_market.common.handler.ex.CustomException;
import com.market.simple.yuus_market.common.jwt.TokenProvider;
import com.market.simple.yuus_market.domains.auth.application.dto.LoginDto;
import com.market.simple.yuus_market.domains.auth.application.dto.TokenDto;
import com.market.simple.yuus_market.domains.auth.domain.AuthToken;
import com.market.simple.yuus_market.domains.auth.domain.AuthTokenRepository;
import com.market.simple.yuus_market.domains.member.domain.Member;
import com.market.simple.yuus_market.domains.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.market.simple.yuus_market.common.handler.ex.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AuthLoginService {
    private final AuthTokenRepository authTokenRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;


    @Transactional
    public TokenDto login(LoginDto loginDto) {
        Optional<Member> byuserId = memberRepository.findByUserId(loginDto.getUserId());

        if (byuserId.isEmpty()) {
            throw new CustomException(NOT_MATCHED_ID);
        }

        String password = byuserId.get().getPassword();
        if (!passwordEncoder.matches(loginDto.getPassword(), password)) {
            throw new CustomException(NOT_MATCHED_PASSWORD);
        }


        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = loginDto.toAuthentication();
        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        //     인증에 성공하면 Authentication을 SecurityContextHolder에 저장할 수 있는 객체로 만들어진다.
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);


        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        AuthToken authToken = AuthToken.builder()
                .idKey(loginDto.getUserId())
                .value(tokenDto.getRefreshToken())
                .build();

        authTokenRepository.save(authToken);

        // 5. 토큰 발급
        return tokenDto;
    }
}
