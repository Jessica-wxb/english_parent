package com.tfjybj.english;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 *入口
 *
 * @author 刘雅雯
 * @version 1.0.0
 * @since 1.0.0 2019-1-24 11:42:33
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.tfjybj.english.provider")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

