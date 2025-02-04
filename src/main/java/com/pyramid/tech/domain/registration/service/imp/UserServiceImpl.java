package com.pyramid.tech.domain.registration.service.imp;

import com.pyramid.tech.domain.registration.model.AppUser;
import com.pyramid.tech.domain.registration.repository.UserRepository;
import com.pyramid.tech.domain.registration.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
    @CachePut(value = "usersCache", key= "#user.id")
    public AppUser save(AppUser user) {


        user.setPassword(encoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);

    }

    @Override
    @Cacheable(value = "usersCache")
    public List<AppUser> findAll() {
        System.out.println("Fetching users from database...");
        return userRepository.findAll();
    }

    @Override
    @Cacheable(value = "usersCache", key = "#id")
    public AppUser selectUser(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public AppUser selectUserByUsername(String username) {

        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User name not found"));

    }

    @Override
    @CacheEvict(value = "usersCache", key = "#id")
    public void delete(Long id) {

        AppUser user = selectUser(id);
        userRepository.delete(user);

    }
}
