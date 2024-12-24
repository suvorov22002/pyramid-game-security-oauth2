package com.pyramid.tech.domain.registration.dto;

/**
 * Created by Suvorov Vassilievitch
 * Date: 23/12/2024
 * Time: 22:03
 * Project Name: pyramid-game-security-oauth2
 */
public class AuthDto {

    public record LoginRequest(String username, String password) {}

    public record Response(String message, String token) {}

}
