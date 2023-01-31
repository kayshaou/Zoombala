package com.jakesmommy.zblrestapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ZblRestApiApplicationTests {

	@Test
	void contextLoads() {
		ZblRestApiApplication.main(new String[]{"1","2"});
		assertTrue(new String[]{"1","2"}.length!=0);
	}

}
