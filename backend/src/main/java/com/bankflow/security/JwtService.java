package com.bankflow.security;

import com.bankflow.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    @Value("${jwt.secret}") private String secret;
    @Value("${jwt.expiration}") private long expiration;

    private Key key(){ return Keys.hmacShaKeyFor(secret.getBytes()); }

    public String generate(User user) {
        return Jwts.builder().setSubject(user.getEmail()).claim("role", user.getRole().name())
                .setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(key(), SignatureAlgorithm.HS256).compact();
    }

    public String email(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean valid(String token) {
        try { email(token); return true; } catch(Exception e) { return false; }
    }
}
