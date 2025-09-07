/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.project.fileshifter.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.fileshifter.entity.ConversionJob;

/**
 *
 * @author ckans
 */
@Repository
public interface ConversionJobRepository extends JpaRepository<ConversionJob, UUID> {
  List<ConversionJob> findAllByUserId(UUID userId);

}
