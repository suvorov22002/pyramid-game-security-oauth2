package com.pyramid.tech.domain.registration.controller;

import com.pyramid.tech.domain.registration.dto.AuthDto;
import com.pyramid.tech.domain.registration.model.AppUser;
import com.pyramid.tech.domain.utils.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Suvorov Vassilievitch
 * Date: 23/12/2024
 * Time: 20:35
 * Project Name: pyramid-game-security-oauth2
 */
@RestController
@RequestMapping("/api/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final TokenService tokenService;
    private final AuthenticationManager manager;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDto.LoginRequest userLogin) {

        log.info("User login: {}", userLogin);
        Authentication auth = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.username(),
                        userLogin.password()));

        SecurityContextHolder.getContext().setAuthentication(auth);
        AppUser userDetails = (AppUser) auth.getPrincipal();

        log.info("Token requested for user :{}", auth.getAuthorities());
        String token = tokenService.generateToken(auth);
        log.debug("JWT token: {}", token);

        AuthDto.Response response = new AuthDto.Response("User logged in  successfully", token);

        return ResponseEntity.ok(response);

    }

}
