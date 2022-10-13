package com.edu.exmg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author autom
 * @date 2022-01-05
 **/
@SpringBootApplication
@MapperScan({"com.edu.exmg.core.mapper", "com.edu.exmg.admin.mapper"})
public class ExmgAdminApplication {


    public static void main(String[] args) {
        SpringApplication.run(ExmgAdminApplication.class, args);
    }

}
