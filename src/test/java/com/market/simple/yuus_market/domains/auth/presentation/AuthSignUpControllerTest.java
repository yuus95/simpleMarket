package com.market.simple.yuus_market.domains.auth.presentation;

import com.google.gson.Gson;
import com.market.simple.yuus_market.domains.auth.application.AuthSignUpService;
import com.market.simple.yuus_market.domains.auth.application.dto.MemberRequestDto;
import com.market.simple.yuus_market.domains.auth.application.dto.MemberResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AuthSignUpControllerTest {
    @Mock
    private AuthSignUpService authSignUpService;

    MockMvc mockMvc;

    @InjectMocks
    private AuthSignUpController authSignUpController;


    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(authSignUpController).build();
    }

    @Test
    public void 회원가입_컨트롤러_테스트() throws Exception {
        //given
        MemberRequestDto memberRequestDto = new MemberRequestDto("테스트", "kkad45", "1234", "010-4710-5883");

        MemberResponseDto memberResponseDto = new MemberResponseDto(memberRequestDto.getUserId());

        //when
        Mockito.when(authSignUpService.signup(Mockito.any())).thenReturn(memberResponseDto);

        //then
        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(memberRequestDto)))
                .andExpect(status().isOk());

//        //then
//        ResultActions resultActions = perform.andExpect(status().isOk());
//
//        final MvcResult mvcResult = perform.andExpect(status().isOk()).andReturn();
//        final String token = mvcResult.getResponse().getContentAsString();
//        Assertions.assertThat(token).isNotNull();
    }
}