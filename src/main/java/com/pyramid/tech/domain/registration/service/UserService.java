package com.pyramid.tech.domain.registration.service;

import com.pyramid.tech.domain.registration.model.AppUser;

import java.util.List;

public interface UserService {

    AppUser save(AppUser user);
    List<AppUser> findAll();
    AppUser selectUser(Long id);
    AppUser selectUserByUsername(String username);
    void delete(Long id);
}
