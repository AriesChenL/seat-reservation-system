package com.lynn.seatsystem;

import com.lynn.seatsystem.util.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SeatSystemApplicationTests {
	@Test
	void contextLoads() {
	}

	@Test
	void genToken() {
		String s = JwtUtils.generateToken(1L);
		System.out.println(s);

		boolean b = JwtUtils.validateToken(s, 1L);
		System.out.println(b);

		Long userId = JwtUtils.extractUserId(s);
		System.out.println(userId);

		boolean tokenExpired = JwtUtils.isTokenExpired(s);
		System.out.println(tokenExpired);
	}
}
