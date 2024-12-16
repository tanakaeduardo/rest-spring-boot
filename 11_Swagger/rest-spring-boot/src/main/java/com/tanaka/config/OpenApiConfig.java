package com.tanaka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Rest API with Java 21 and Spring Boot 3")
						.version("v1")
						.description("Rest API with Java 21 and Spring Boot 3")
						.termsOfService("https://github.com/tanakaeduardo/rest-spring-boot")
						.license(
							new License()
								.name("Apache 2.0")
								.url("https://github.com/tanakaeduardo/rest-spring-boot")
							)
						);
	}

}
