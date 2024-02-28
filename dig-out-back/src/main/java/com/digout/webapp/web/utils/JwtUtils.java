package com.digout.webapp.web.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Base64;
import java.util.HashMap;
import java.util.function.Function;

@Component
public class JwtUtils {

    private SecretKey key;
    private static final int EXPIRATION_PERIOD = 3600000;

    public JwtUtils() {
        String SECRET_STRING = "15161718";
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_STRING.getBytes(StandardCharsets.UTF_8));
        this.key = new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    public String generateToken(String nickname) {
        return Jwts.builder()
                .subject(nickname)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_PERIOD))
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(HashMap<String, Object> claims, String nickname) {
        return Jwts.builder()
                .claims(claims)
                .subject(nickname)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_PERIOD))
                .signWith(key)
                .compact();
    }

    public String extractNickname(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        return claimsTFunction.apply(Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload());
    }

    public boolean isTokenValid(String token, String nickname) {
        final String tokenNickname = extractNickname(token);
        return tokenNickname.equals(nickname) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        var tokenDate = extractClaims(token, Claims::getExpiration);
        return tokenDate.before(new java.util.Date());
    }
}
