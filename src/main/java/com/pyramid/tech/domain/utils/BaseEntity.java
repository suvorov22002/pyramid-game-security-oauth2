package com.pyramid.tech.domain.utils;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created by Suvorov Vassilievitch
 * Date: 23/12/2024
 * Time: 15:42
 * Project Name: pyramid-game-security-oauth2
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updateAt;
    private String updateBy;

}
