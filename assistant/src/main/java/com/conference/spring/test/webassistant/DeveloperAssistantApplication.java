package com.conference.spring.test.webassistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(AssistantProperties.class)
public class DeveloperAssistantApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeveloperAssistantApplication.class, args);
	}
}
