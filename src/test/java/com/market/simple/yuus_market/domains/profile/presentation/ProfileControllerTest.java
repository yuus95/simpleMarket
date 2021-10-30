package com.market.simple.yuus_market.domains.profile.presentation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ProfileCOntroller나 Environment 모두 자바 클래스이기 때문에 쉽게 테스트 가능
 * 생성자 DI로 인해 주입 가능  @Autowired 였으면  자바로만 테스트 불가능
 */
class ProfileControllerTest {

    @Test
    public void real_profile이_조회된다() {
        //given
        String expectedProfile = "real";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");

        ProfileController profileController = new ProfileController(env);

        //when
        String profile = profileController.profile();

        //then
        Assertions.assertThat(profile).isEqualTo("real");

    }

    @Test
    public void real_profile이_없으면_첫_번째가조회된다() {
        //given
        String expectedProfile = "oauth";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("real-db");

        ProfileController profileController = new ProfileController(env);

        //when
        String profile = profileController.profile();

        //then
        Assertions.assertThat(profile).isEqualTo("oauth");

    }

    @Test
    public void active_profile_이없으면_default조회() {
        //given
        String expectedProfile = "default";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        ProfileController profileController = new ProfileController(env);

        //when
        String profile = profileController.profile();

        //then
        Assertions.assertThat(profile).isEqualTo("default");

    }
}