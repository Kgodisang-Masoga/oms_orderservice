package com.fnb.orderservice.security.securityImpl;

import com.fnb.orderservice.security.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-ms}")
    private Long expirationMs;

    private SecretKey signingKey(){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Claims claims = parseClaims(token);

            return claims.getExpiration().after(new Date());
        } catch (ExpiredJwtException e) {
            return false;
        }
    }

    @Override
    public long extractCustomerId(String token) {
        Long customerId = parseClaims(token).get("customerId", Long.class);
        return customerId;
    }

    @Override
    public String extractEmailFromToken(String token) {
        return parseClaims(token).getSubject();
    }

    @Override
    public String extractRole(String token) {
        return parseClaims(token).get("ROLE", String.class);
    }

    private Claims parseClaims(String token){
        return Jwts.parser()
                .verifyWith(signingKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
