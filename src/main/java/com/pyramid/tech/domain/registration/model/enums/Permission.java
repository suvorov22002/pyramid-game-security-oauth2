package com.pyramid.tech.domain.registration.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_CREATE("admin:create"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete");


    private final String autorisation;

    Permission(String autorisation) {
        this.autorisation = autorisation;
    }
}
