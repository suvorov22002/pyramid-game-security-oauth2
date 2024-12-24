package com.pyramid.tech.domain.registration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pyramid.tech.domain.registration.model.enums.Role;
import com.pyramid.tech.domain.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Suvorov Vassilievitch
 * Date: 23/12/2024
 * Time: 15:24
 * Project Name: pyramid-game-security-oauth2
 */
@Getter
@Setter
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "USER_NAME" }) })
@Entity(name = "PYRAM_USER")
@NoArgsConstructor
public class AppUser extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String username;

    @JsonIgnore
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ENABLED")
    private Boolean enabled = Boolean.TRUE;

    @Column(name = "LOCKED")
    private Boolean locked = Boolean.FALSE;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE")
    private Role role = Role.USER;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.role.name()));
        return authorities;

    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
