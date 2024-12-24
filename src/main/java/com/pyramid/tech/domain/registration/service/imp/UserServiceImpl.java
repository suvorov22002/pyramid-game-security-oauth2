package com.pyramid.tech.domain.registration.service.imp;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.pyramid.tech.core.config.Encoders;
import com.pyramid.tech.domain.registration.dto.UserDto;
import com.pyramid.tech.domain.registration.model.AppUser;
import com.pyramid.tech.domain.registration.model.enums.Role;
import com.pyramid.tech.domain.registration.repository.UserRepository;
import com.pyramid.tech.domain.registration.service.UserService;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Created by Suvorov Vassilievitch
 * Date: 24/12/2024
 * Time: 01:28
 * Project Name: pyramid-game-security-oauth2
 */
@Service
@Transactional
@RequiredArgsConstructor
@Getter
@Setter
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public AppUser save(UserDto.UserRequest userrequest) {

        AppUser user = new AppUser();
        user.setRole(Objects.nonNull(userrequest.role()) ? Role.valueOf(userrequest.role()) : Role.USER);
        user.setUsername(userrequest.username());
        user.setPassword(encoder.encode(userrequest.password()));
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);

    }

    @Override
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public AppUser selectUser(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("User not found"));
    }

    @Override
    public AppUser selectUserByUsername(String username) {

        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User name not found"));

    }

    @Override
    public void delete(Long id) {

        AppUser user = selectUser(id);
        userRepository.delete(user);

    }
}
