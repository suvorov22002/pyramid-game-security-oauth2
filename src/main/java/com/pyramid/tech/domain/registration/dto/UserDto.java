package com.pyramid.tech.domain.registration.dto;

/**
 * Created by Suvorov Vassilievitch
 * Date: 24/12/2024
 * Time: 01:40
 * Project Name: pyramid-game-security-oauth2
 */
public class UserDto {

    public record UserRequest(String username, String password, String role, Boolean enabled) {}

    public record Response(String username, String role, Boolean enabled) {}

}
