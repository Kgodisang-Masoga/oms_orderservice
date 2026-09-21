package com.fnb.orderservice.security;

public interface JwtService {

    boolean validateToken(String token, String email);

    String extractEmailFromToken(String token);

    String extractRole(String token);

}
