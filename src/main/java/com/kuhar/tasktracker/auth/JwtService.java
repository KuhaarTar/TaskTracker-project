package com.kuhar.tasktracker.auth;

import com.kuhar.tasktracker.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${auth.token.secret}")
    private String secret;

    public String createToken(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSecret())
                .compact();
    }

    public String createToken(Map<String, Object> claims, UserDetails user) {
        return Jwts.builder()
                .addClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSecret())
                .compact();
    }

    public String createRefreshToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 30))
                .signWith(getSecret())
                .compact();
    }

    public String extractEmail(String token) {
        return parseToken(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        return (extractEmail(token).equals(userDetails.getUsername()) && isNotExpired(token));
    }

    private Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecret())
                .build()
                .parseClaimsJws(token).getBody();
    }


    private boolean isNotExpired(String token) {
        return !parseToken(token).getExpiration().before(new Date());
    }

    private SecretKey getSecret() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
}
