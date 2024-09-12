package com.notifications.streaming.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

import com.notifications.streaming.models.UserDetails;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "3AEC9E4D6BED514D4943CE2B9F2F4675TYTR789764YUPLMH78HG564FHF";
    private static final long EXPIRATION_TIME = 3 * 60 * 1000;

    public String generateToken(UserDetails request) {

        Map<String, String> authDetails = new HashMap<>();


        Map<String, Object> claims = new HashMap<>();
        try {
            String jwtToken = createToken(claims, request.getName());
            System.out.println(jwtToken);
            return jwtToken;
        } catch (Exception e1) {
            System.out.println("e1" + e1);
        }
        return "error occured";
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        Claims response = Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
        System.out.println("These are the claims: " + response);
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean isTokenExpired(String token) {
        final Claims claims = extractAllClaims(token);
        final Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    public String getUserName(String token) {
        final Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

}
