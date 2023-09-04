package com.example.enterprisecredit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@EnableSwagger2WebMvc
@SpringBootApplication
@MapperScan("com.example.enterprisecredit.mapper")
public class EnterpriseCreditApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnterpriseCreditApplication.class, args);
	}

}
