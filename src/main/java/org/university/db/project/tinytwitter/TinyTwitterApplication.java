package org.university.db.project.tinytwitter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("org.university.db.project.tinytwitter")
public class TinyTwitterApplication {
    public static void main(String[] args) {
        SpringApplication.run(TinyTwitterApplication.class, args);
    }
}
