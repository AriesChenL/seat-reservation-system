package com.lynn.seatsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@MapperScan("com.lynn.seatsystem.mapper")
@SpringBootApplication
public class SeatSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeatSystemApplication.class, args);
	}

}
