/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.project.fileshifter.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.fileshifter.entity.User;
import com.project.fileshifter.repository.UserRepository;

/**
 *
 * @author ckans
 */
@Service
public class AuthService {
  @Autowired
  private final PasswordEncoder passwordEncoder;
  private UserRepository userRepository;

  public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User createUser(User user) {
    user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
    return userRepository.save(user);
  }

  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public boolean checkPassword(String rawPassword, String encodedPassword) {
    return passwordEncoder.matches(rawPassword, encodedPassword);
  }

}
