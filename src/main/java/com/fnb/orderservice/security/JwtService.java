package com.fnb.orderservice.security;

public interface JwtService {

    boolean validateToken(String token);

    long extractCustomerId(String token);

    String extractEmailFromToken(String token);

    String extractRole(String token);

}
