package com.market.simple.yuus_market.domains.auth.application;

import com.market.simple.yuus_market.common.handler.ex.CustomException;
import com.market.simple.yuus_market.domains.auth.application.dto.MemberRequestDto;
import com.market.simple.yuus_market.domains.auth.application.dto.MemberResponseDto;
import com.market.simple.yuus_market.domains.member.domain.Member;
import com.market.simple.yuus_market.domains.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.market.simple.yuus_market.common.handler.ex.ErrorCode.DUPLICATE_MEMBER;


@Service
@RequiredArgsConstructor
public class AuthSignUpService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional // write (insert,update,delete)할 때 사용
    public MemberResponseDto signup(MemberRequestDto memberRequestDto) {
        if (memberRepository.existsByUserId(memberRequestDto.getUserId())) {
            throw new CustomException(DUPLICATE_MEMBER);
        }

        Member member = memberRequestDto.toMember(passwordEncoder);
        return MemberResponseDto.of(memberRepository.save(member));
    }
}
