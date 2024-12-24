package com.pyramid.tech.domain.registration.service;

import com.pyramid.tech.domain.registration.dto.UserDto;
import com.pyramid.tech.domain.registration.model.AppUser;

import java.util.List;

public interface UserService {

    AppUser save(UserDto.UserRequest user);
    List<AppUser> findAll();
    AppUser selectUser(Long id);
    AppUser selectUserByUsername(String username);
    void delete(Long id);
}
