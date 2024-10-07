package com.lynn.seatsystem.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.Charset;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Author: Lynn
 * Date: 2024/10/06 下午7:04
 */
public class JwtUtils {
    private static final String SECRET_KEY = "lynn-seat-secret-key-NhHGSKEUjdyFAGtgJDGuiOLJJGTRHtyRRrrrHC";
    private static final Key SIGNING_KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(Charset.defaultCharset()));


    /**
     * 1 day in milliseconds
     */
    private static final long EXPIRATION_TIME = 86400000;

    public static String generateToken(Long userId) {
        Map<String, Object> claims = new HashMap<>(1);
        return createToken(claims, userId.toString());
    }

    private static String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SIGNING_KEY)
                .compact();
    }

    public static Long extractUserId(String token) {
        return Long.parseLong(extractClaim(token, Claims::getSubject));
    }

    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SIGNING_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public static boolean validateToken(String token, Long userId) {
        final Long extractedUserId = extractUserId(token);
        return (extractedUserId.equals(userId) && !isTokenExpired(token));
    }
}