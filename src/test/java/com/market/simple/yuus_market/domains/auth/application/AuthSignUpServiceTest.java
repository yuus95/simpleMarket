package com.market.simple.yuus_market.domains.auth.application;

import com.market.simple.yuus_market.domains.auth.application.dto.MemberRequestDto;
import com.market.simple.yuus_market.domains.member.domain.Member;
import com.market.simple.yuus_market.domains.member.domain.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
class AuthSignUpServiceTest {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


    @Spy
    PasswordEncoder passwordEncoder = bCryptPasswordEncoder;

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    AuthSignUpService authSignUpService;

    @Test
    public void 멤버생성() throws Exception {
        //given
        MemberRequestDto memberRequestDto = new MemberRequestDto("테스트", "kkad45", "1234", "010-4710-5883");

        Member member = memberRequestDto.toMember(passwordEncoder);

        Mockito.when(memberRepository.existsByUserId("kkad45")).thenReturn(false);
        Mockito.when(memberRepository.save(Mockito.any(Member.class))).thenReturn(member);

        //when
        authSignUpService.signup(memberRequestDto);

        //then


        Mockito.verify(memberRepository).save(Mockito.any(Member.class));
        Assertions.assertThat(memberRequestDto.getUserId()).isEqualTo(member.getUserId());

    }


}