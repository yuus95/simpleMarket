package com.market.simple.yuus_market.domains.auth;

import com.google.gson.Gson;
import com.market.simple.yuus_market.domains.auth.application.dto.LoginDto;
import com.market.simple.yuus_market.domains.auth.presentation.AuthLoignController;
import com.market.simple.yuus_market.domains.member.domain.AuthProvider;
import com.market.simple.yuus_market.domains.member.domain.Authority;
import com.market.simple.yuus_market.domains.member.domain.Member;
import com.market.simple.yuus_market.domains.member.domain.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class LoginTest {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AuthLoignController authLoignController;

    MockMvc mockMvc;

    @BeforeEach
    public void init(){
        Member build = Member.builder()
                .userId("kkad45")
                .password(passwordEncoder.encode("1234"))
                .provider(AuthProvider.local)
                .nickname("nickname")
                .authority(Authority.ROLE_USER)
                .phone("01047105883")
                .build();
        memberRepository.save(build);

        mockMvc= MockMvcBuilders.standaloneSetup(authLoignController).build();
    }

    @Test
    public void 로그인통합테스트() throws Exception{
        //given
        LoginDto loginDto = new LoginDto();
        loginDto.setUserId("kkad45");
        loginDto.setPassword("1234");
        //when

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(loginDto)))
                .andExpect(status().isOk());
    }


}
