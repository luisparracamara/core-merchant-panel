package com.core.merchant.panel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.core.merchant.panel")
@EnableJpaRepositories(basePackages = "com.core.merchant.panel")
@EntityScan(basePackages = "com.core.merchant.panel.entity")
public class CoreMerchantPanelApplication {

	public static void main(String[] args) {
        System.setProperty("spring.config.location", "config/");
        SpringApplication.run(CoreMerchantPanelApplication.class, args);
	}

}
