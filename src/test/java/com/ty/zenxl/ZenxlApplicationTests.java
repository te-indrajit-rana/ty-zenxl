package com.ty.zenxl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ZenxlApplicationTests {

	@Test
	@DisplayName("Application context loaded successfully.")
	void contextLoads() {

	}

}
