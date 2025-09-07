/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.project.fileshifter.controller;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.fileshifter.util.JwtUtil;

/**
 *
 * @author ckans
 */

@RestController

public class ProtectedController {

  private final JwtUtil jwtUtil;

  public ProtectedController(JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
  }

 
  /**
   * Endpoint to check if the user is logged in. The request should contain a
   * JWT token in the "token" cookie. If the token is invalid or missing, the
   * server will return a 401 Unauthorized response. If the token is valid, the
   * server will return a 200 OK response with a message indicating that the user
   * is logged in.
   * @param token The JWT token provided by the client.
   * @return A ResponseEntity containing a message indicating whether the user is
   * logged in.
   */
  @GetMapping("/api/protected")
  public ResponseEntity<Map<String, String>> isUserLoggedIn(
      @CookieValue(value = "token", required = false) String token) {

    if (token == null || !jwtUtil.isTokenValid(token, jwtUtil.extractUserId(token))) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
          .body(Collections.singletonMap("message", "Unauthorized"));
    }
    return new ResponseEntity<>(Collections.singletonMap("message", "This is protected data"), HttpStatus.OK);
  }

  

}
