/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.project.fileshifter.util;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author ckans
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
  @Autowired
  private JwtUtil jwtUtil;

  @SuppressWarnings("null")
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    // Find the token cookie
    String token = null;
    if (request.getCookies() != null) {
      for (Cookie cookie : request.getCookies()) {
        if (cookie.getName().equals("token")) {
          token = cookie.getValue();
          break;
        }
      }
    }

    if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      try {
        // If the token is valid, create an Authentication object
        if (jwtUtil.isTokenValid(token)) {
          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
              new User("authenticatedUser", "", new ArrayList<>()), null, new ArrayList<>());
          // Set the user in the security context
          SecurityContextHolder.getContext().setAuthentication(authToken);
        }
      } catch (Exception e) {
        // Token is invalid, do nothing and let the request fail.
      }
    }

    filterChain.doFilter(request, response);

  }

}
