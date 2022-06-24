package com.ty.zenxl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@EnableAsync
@SpringBootApplication
@SecurityScheme(name = "zenxl-api", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
@OpenAPIDefinition(info = @Info(title = "ZeNXL API", version = "1.0", description = "ZeNXL Application Development"))
public class ZenxlApplication{

	public static void main(String[] args) {
		SpringApplication.run(ZenxlApplication.class, args);
	}

}
