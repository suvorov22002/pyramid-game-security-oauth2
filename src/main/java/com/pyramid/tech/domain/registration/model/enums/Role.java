package com.pyramid.tech.domain.registration.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Set;

import static com.pyramid.tech.domain.registration.model.enums.Permission.*;

@Getter
public enum Role {

    ADMIN(
        Set.of(
            ADMIN_READ,
            ADMIN_DELETE,
            ADMIN_CREATE,
            ADMIN_UPDATE
        )
    ),
    USER(
            Collections.emptySet()
    );

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

}
