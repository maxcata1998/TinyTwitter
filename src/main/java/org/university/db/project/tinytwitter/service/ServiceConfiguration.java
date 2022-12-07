package org.university.db.project.tinytwitter.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@MapperScan("org.university.db.project.tinytwitter.dao")
@PropertySource("classpath:application.properties")
public class ServiceConfiguration {

}
