package com.pyramid.tech.domain.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Suvorov Vassilievitch
 * Date: 24/12/2024
 * Time: 18:05
 * Project Name: pyramid-game-security-oauth2
 */
@Configuration
public class ModelMapperConfig {

    /*
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    */

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper().registerModule(new RecordModule());
    }

}
