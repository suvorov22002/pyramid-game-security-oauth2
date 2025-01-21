package com.pyramid.tech.domain.registration.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Suvorov Vassilievitch
 * Date: 21/01/2025
 * Time: 20:49
 * Project Name: pyramid-game-security-oauth2
 */
@Getter
@Setter
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "NAME" }) })
@Entity(name = "PYRAM_ROLE")
@NoArgsConstructor
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }

}
