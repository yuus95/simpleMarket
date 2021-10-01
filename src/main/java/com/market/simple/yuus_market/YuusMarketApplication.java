package com.market.simple.yuus_market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class YuusMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(YuusMarketApplication.class, args);
	}

}
