package com.pyramid.tech.core.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomDatasourceConfig {

    private String username;
    private String password;
    private String url;
}
