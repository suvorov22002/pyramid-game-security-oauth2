package com.pyramid.tech.core.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Suvorov Vassilievitch
 * Date: 23/12/2024
 * Time: 13:43
 * Project Name: pyramid-game-security-oauth2
 */
@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

    private final Environment env;

    //@Bean
    //@Primary
    //@ConfigurationProperties("spring.datasource")
    HikariDataSource hikariDataSource() {

        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    @Profile({"postgres"})
    @ConfigurationProperties("spring.datasource")
    public CustomDatasourceConfig datasourceConfig() {
        return new CustomDatasourceConfig();
    }

    @Bean
    @Profile({"postgres"})
    HikariDataSource hikariDataSourceConfig() {

        HikariConfig hikariConfig = new HikariConfig();

        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("datasource.properties")) {
            prop.load(input);
        }catch (IOException ex) {
            ex.printStackTrace();
        }

        hikariConfig.setAutoCommit(env.getProperty("spring.datasource.hikari.auto-commit", Boolean.class));
        hikariConfig.setConnectionTimeout(env.getProperty("spring.datasource.hikari.connection-timeout", Long.class));
        hikariConfig.setIdleTimeout(env.getProperty("spring.datasource.hikari.idle-timeout", Long.class));
        hikariConfig.setMaxLifetime(env.getProperty("spring.datasource.hikari.max-lifetime", Long.class));
        hikariConfig.setMaximumPoolSize(env.getProperty("spring.datasource.hikari.maximum-pool-size", Integer.class));
        hikariConfig.setMinimumIdle(env.getProperty("spring.datasource.hikari.minimum-idle", Integer.class));
        hikariConfig.setConnectionTestQuery(env.getProperty("spring.datasource.hikari.connection-test-query", String.class));
        hikariConfig.setPoolName(env.getProperty("spring.datasource.hikari.pool-name", String.class));
    //    hikariConfig.setDataSourceProperties(prop);
        // Set the default schema
        hikariConfig.addDataSourceProperty("currentSchema", "pyramid_owner");
        hikariConfig.setJdbcUrl(datasourceConfig().getUrl());
        hikariConfig.setUsername(datasourceConfig().getUsername());
        hikariConfig.setPassword(datasourceConfig().getPassword());

        return new HikariDataSource(hikariConfig);
    }


/*
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setLocation(new ClassPathResource("hikari.properties"));
        configurer.setIgnoreUnresolvablePlaceholders(true);
        return configurer;
    }*/
    //@Bean
    //@Profile({"postgres"})
    public DataSource dataSource() {

        HikariConfig config = new HikariConfig("/hikari.properties");

        Properties prop = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("datasource.properties")) {
            prop.load(input);
        }catch (IOException ex) {
            ex.printStackTrace();
        }

        config.setDataSourceProperties(prop);
        config.setJdbcUrl(datasourceConfig().getUrl());
        config.setUsername(datasourceConfig().getUsername());
        config.setPassword(datasourceConfig().getPassword());

        return new HikariDataSource(config);
    }

    //@Bean
    //@DependsOn("flyway")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        // Additional configuration
        return em;
    }

}
