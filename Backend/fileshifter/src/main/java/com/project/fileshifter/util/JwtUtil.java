/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.project.fileshifter.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 *
 * @author ckans
 */
@Component
public class JwtUtil {

  private final SecretKey key;
  private final long expiration = 1000 * 60 * 60 * 10;

  public JwtUtil(@Value("${jwt.key}") String secret) {
    byte[] keyBytes = Base64.getDecoder().decode(secret.getBytes(StandardCharsets.UTF_8));
    this.key = new SecretKeySpec(keyBytes,  "HmacSHA256");
  }

  public String generateToken(UUID userId) {
    return Jwts.builder()
      
        .claim("userId", userId.toString())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(key)
        .compact();
  }

   private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

  // Extract username from token
  public String extractUserId(String token) {
    return extractAllClaims(token).get("userId", String.class);
  }

  // Check if token is expired
  private boolean isTokenExpired(String token) {
    return extractAllClaims(token).getExpiration().before(new Date());
  }

  // Validate token for a username
  public boolean isTokenValid(String token, String username) {
    final String tokenUsername = extractUserId(token);
    return (tokenUsername.equals(username) && !isTokenExpired(token));
  }

  public UUID getUserIdFromToken(String token) {
    String userIdStr = extractAllClaims(token).get("userId", String.class);
    return UUID.fromString(userIdStr);
}
}