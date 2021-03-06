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


        // 1. Login ID/PW ??? ???????????? AuthenticationToken ??????
        UsernamePasswordAuthenticationToken authenticationToken = loginDto.toAuthentication();
        // 2. ????????? ?????? (????????? ???????????? ??????) ??? ??????????????? ??????
        //    authenticate ???????????? ????????? ??? ??? CustomUserDetailsService ?????? ???????????? loadUserByUsername ???????????? ?????????
        //     ????????? ???????????? Authentication??? SecurityContextHolder??? ????????? ??? ?????? ????????? ???????????????.
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. ?????? ????????? ???????????? JWT ?????? ??????
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken ??????
        AuthToken authToken = AuthToken.builder()
                .idKey(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        authTokenRepository.save(authToken);

        // 5. ?????? ??????
        return tokenDto;
    }
}
