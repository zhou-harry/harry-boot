package com.harry.base.db.config;

import com.harry.base.db.properties.DatabaseProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DatabaseProperties.class)
@MapperScan("${harry.base.datasource.mapperLocation}")
public class DatabaseConfig {

}
