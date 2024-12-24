package com.pyramid.tech.domain.registration.controller;

import com.pyramid.tech.domain.registration.dto.UserDto;
import com.pyramid.tech.domain.registration.model.AppUser;
import com.pyramid.tech.domain.registration.model.enums.Role;
import com.pyramid.tech.domain.registration.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Created by Suvorov Vassilievitch
 * Date: 23/12/2024
 * Time: 23:30
 * Project Name: pyramid-game-security-oauth2
 */
@RestController
@RequestMapping("/api/users")
@Slf4j
@RequiredArgsConstructor
@Getter
@Setter
public class UserController {

    private final UserService userService;

    @GetMapping("")
    ResponseEntity<List<UserDto.Response>> selectAllUsers() {

        List<AppUser> users = userService.findAll();
        List<UserDto.Response> responses = users.stream()
                .map(u -> new UserDto.Response(
                        u.getUsername(),
                        u.getRole().name(),
                        u.getEnabled()
                )).toList();

        return ResponseEntity.ok(responses);

    }

    @PostMapping("")
    ResponseEntity<UserDto.Response> createUser(@RequestBody UserDto.UserRequest userrequest) {

        AppUser user = new AppUser();
        user.setRole(Objects.nonNull(userrequest.role()) ? Role.valueOf(userrequest.role()) : Role.USER);
        user.setUsername(userrequest.username());
        user.setPassword(userrequest.password());
        AppUser u = userService.save(user);

        return ResponseEntity.ok(new UserDto.Response(
                u.getUsername(),
                u.getRole().name(),
                u.getEnabled()
        ));
    }

}
