/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.project.fileshifter.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.fileshifter.entity.User;

/**
 *
 * @author ckans
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
  Optional<User> findByEmail(String email);
}
