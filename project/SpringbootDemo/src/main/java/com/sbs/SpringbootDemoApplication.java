package com.sbs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author lixin
 */
@SpringBootApplication
//@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@MapperScan("com.sbs.system.*.mapper")
public class SpringbootDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
    	System.out.println(
			    "			.__  .__       .__         			\n"
			   +"			|  | |__|__  __|__| ____  			\n"
			   +"			|  | |  \\  \\/  /  |/    \\        \n"
			   +" 			|  |_|  |>    <|  |   |  \\         \n"
			   +" 			|____/__/__/\\_ \\__|___|  / ");
    }
}
