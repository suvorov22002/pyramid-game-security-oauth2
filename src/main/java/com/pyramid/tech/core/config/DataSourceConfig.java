package com.pyramid.tech.core.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by Suvorov Vassilievitch
 * Date: 23/12/2024
 * Time: 13:43
 * Project Name: pyramid-game-security-oauth2
 */
@Configuration
public class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.main")
    HikariDataSource hikariDataSource() {

        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();
    }
}
