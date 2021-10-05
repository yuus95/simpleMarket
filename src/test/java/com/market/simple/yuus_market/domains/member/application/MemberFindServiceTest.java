package com.market.simple.yuus_market.domains.member.application;

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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class MemberFindServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @Spy
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MemberFindService memberFindService;

    @Test
    public void 유저찾기_테스트() throws Exception{
        //given
        MemberRequestDto memberRequestDto = new MemberRequestDto("테스트", "kkad45", "1234", "010-4710-5883");

        Member member = memberRequestDto.toMember(passwordEncoder);

        Mockito.when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

        //when
        Member byId = memberFindService.findById(1L);

        //then
        Assertions.assertThat(byId).isEqualTo(member);

    }




}