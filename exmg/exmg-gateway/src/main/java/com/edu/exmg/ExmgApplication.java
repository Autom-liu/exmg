package com.edu.exmg;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan({ "com.edu.exmg.core.mapper", "com.edu.exmg.gateway.mapper" })
public class ExmgApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(ExmgApplication.class, args);
	}
}
