package com.market.simple.yuus_market.domains.auth.presentation;

import com.google.gson.Gson;
import com.market.simple.yuus_market.domains.auth.application.AuthLoginService;
import com.market.simple.yuus_market.domains.auth.application.dto.LoginDto;
import com.market.simple.yuus_market.domains.auth.application.dto.TokenDto;
import com.market.simple.yuus_market.domains.member.domain.AuthProvider;
import com.market.simple.yuus_market.domains.member.domain.Authority;
import com.market.simple.yuus_market.domains.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class AuthLoignControllerTest {

    MockMvc mockMvc;


    @Mock
    private AuthLoginService authLoginService;

    @InjectMocks
    private AuthLoignController authLoignController;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(authLoignController).build();
    }

    @Test
    public void 로그인_테스트() throws Exception {
        //given
        Member.builder()
                .userId("kkad45")
                .password("1234'")
                .authority(Authority.ROLE_USER)
                .nickname("nickname")
                .phone("01040404040")
                .provider(AuthProvider.local)
                .build();

        LoginDto loginDto = new LoginDto();
        loginDto.setUserId("kkad45");
        loginDto.setPassword("1234");

        TokenDto tokenDto = new TokenDto();
        //when
        Mockito.when(authLoginService.login(Mockito.any())).thenReturn(tokenDto);
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(loginDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}