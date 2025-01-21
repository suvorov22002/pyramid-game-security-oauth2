package com.pyramid.tech.domain.registration.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pyramid.tech.domain.registration.model.enums.Role;
import com.pyramid.tech.domain.utils.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
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
@Table(name = "PYRAM_USER", uniqueConstraints = { @UniqueConstraint(columnNames = { "USER_NAME" }) })
@Entity
@NoArgsConstructor
public class AppUser extends BaseEntity implements UserDetails, Serializable {

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

    @Column(name = "ACCOUNT_EXPIRED")
    private boolean accountExpired = Boolean.FALSE;

    @Column(name = "ACCOUNT_LOCKED")
    private boolean accountLocked = Boolean.FALSE;

    @Column(name = "CREDENTIALS_EXPIRED")
    private boolean credentialsExpired = Boolean.FALSE;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USERS_AUTHORITIES", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID"))
    @OrderBy
    @JsonIgnore
    private Collection<Authority> authorities;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
