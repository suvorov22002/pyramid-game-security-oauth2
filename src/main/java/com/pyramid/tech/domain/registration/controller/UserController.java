package com.pyramid.tech.domain.registration.controller;

import com.pyramid.tech.domain.registration.dto.UserDto;
import com.pyramid.tech.domain.registration.model.AppUser;
import com.pyramid.tech.domain.registration.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Suvorov Vassilievitch
 * Date: 23/12/2024
 * Time: 23:30
 * Project Name: pyramid-game-security-oauth2
 */
@RestController
@RequestMapping("/api/users")
@Tag(name = "User API V1", description = "Operations related to User V1")
@Slf4j
@RequiredArgsConstructor
@Getter
@Setter
@CrossOrigin
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("")
    @Operation(summary = "Get All Users")
    ResponseEntity<List<UserDto.Response>> selectAllUsers() {

        List<AppUser> users = userService.findAll();
        System.out.println("ALL USSSSSSSSSSSSSER: " + users.size());
        List<UserDto.Response> responses = users.stream()
                .map(u -> new UserDto.Response(
                        u.getUsername(),
                        u.getPassword(),
                        u.getEnabled()
                ))
        //        .map(u -> modelMapper.map(u, UserDto.Response.class))
                .peek(System.out::println)
                .toList();

        return ResponseEntity.ok(responses);

    }

    @GetMapping("/username/{username}")
    @Operation(summary = "Fetch One User by username")
    ResponseEntity<UserDto.Response> fetchUniqueUsername(@PathVariable String username) {

        AppUser user = userService.selectUserByUsername(username);
        UserDto.Response response = new UserDto.Response(
                user.getUsername(),
                "ADMIN",
                user.getEnabled());

        return ResponseEntity.ok(response);

    }

    @GetMapping("/{userId}")
    @Operation(summary = "Fetch User by his id")
    ResponseEntity<UserDto.Response> fetchUniqueUser(@PathVariable Long userId) {

        AppUser user = userService.selectUser(userId);
        UserDto.Response response = new UserDto.Response(
                user.getUsername(),
                "ADMIN",
                user.getEnabled());

        return ResponseEntity.ok(response);

    }

    @PostMapping("")
    @Operation(summary = "Create new User V1")
    ResponseEntity<UserDto.Response> createUser(@RequestBody UserDto.UserRequest userrequest) {

        AppUser user = new AppUser();
        /*
        user.setRole(Objects.nonNull(userrequest.role()) ? Role.valueOf(userrequest.role()) : Role.USER);
        user.setUsername(userrequest.username());
        user.setPassword(userrequest.password());
        */
        user = modelMapper.map(userrequest, AppUser.class);
        AppUser u = userService.save(user);

        return ResponseEntity.ok(new UserDto.Response(
                u.getUsername(),
                "ADMIN",
                u.getEnabled()
        ));
    }

}
