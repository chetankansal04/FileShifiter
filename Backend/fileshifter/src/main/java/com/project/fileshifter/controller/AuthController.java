/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.project.fileshifter.controller;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.fileshifter.dto.LoginRequest;
import com.project.fileshifter.dto.RegisterRequest;
import com.project.fileshifter.entity.User;
import com.project.fileshifter.service.AuthService;
import com.project.fileshifter.util.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ckans
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  private final JwtUtil jwtUtil;
  private final AuthService authService;

  public AuthController(AuthService authService, JwtUtil jwtUtil) {
    this.jwtUtil = jwtUtil;
    this.authService = authService;
  }

  /**
   * Endpoint to get the user ID of the user that is logged in. Will return 401
   * Unauthorized if the token is invalid or missing.
   * 
   * @param token The JWT token provided by the client.
   * @return A ResponseEntity containing the user ID of the logged in user.
   */
  @GetMapping("/me")
  public ResponseEntity<?> me(@CookieValue(value = "token", required = false) String token) {
    if (token == null || !jwtUtil.isTokenValid(token, jwtUtil.extractUserId(token))) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    UUID userId = jwtUtil.getUserIdFromToken(token);
    return ResponseEntity.ok(Collections.singletonMap("userId", userId));
  }

  /**
   * Endpoint to register a new user. The request body should contain the email
   * and password of the user to be registered.
   * 
   * @param registerRequest The request body containing the email and password of
   *                        the user.
   * @return A ResponseEntity indicating whether the user was registered
   *         successfully.
   */
  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest) {
    User newUser = new User();
    newUser.setEmail(registerRequest.getEmail());
    newUser.setPasswordHash(registerRequest.getPassword());

    authService.createUser(newUser);
    return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
  }

  /**
   * Endpoint to login a user. The request body should contain the email and
   * password of the user. The response will include a cookie with a JWT token
   * that can be used to authenticate the user. If the email or password is
   * invalid, the server will return a 401 Unauthorized response.
   */
  @PostMapping("/login")
  public ResponseEntity<Map<String, String>> loginUser(@RequestBody LoginRequest loginRequest,
      HttpServletResponse response) {
    Optional<User> optionalUser = authService.findByEmail(loginRequest.getEmail());
    if (optionalUser.isPresent()) {
      User user = optionalUser.get();
      if (authService.checkPassword(loginRequest.getPassword(), user.getPasswordHash())) {
        String token = jwtUtil.generateToken(user.getId());
        ResponseCookie cookie = ResponseCookie.from("token", token)
            .httpOnly(true)
            .secure(false)
            .path("/")
            .maxAge(60 * 60 * 10)
            .sameSite("lax")
            .build();

        response.addHeader("Set-Cookie", cookie.toString());

        return new ResponseEntity<>(Collections.singletonMap("message", "Login Succesful"), HttpStatus.OK);
      }
    }

    return new ResponseEntity<>(Collections.singletonMap("message", "Invalid email or password."),
        HttpStatus.UNAUTHORIZED);
  }

  /**
   * Endpoint to check if a user is logged in. If the user is logged in, the
   * server will return a 200 OK response with a JSON object containing a single
   * key-value pair with the key "status" and the value "ok". If the user is not
   * logged in, the server will return a 401 Unauthorized response.
   */
  @GetMapping("/check")
  public ResponseEntity<?> checkAuth() {
    // If this endpoint is reached, the user is authenticated by the JWT filter.
    return ResponseEntity.ok(Collections.singletonMap("status", "ok"));
  }

}
