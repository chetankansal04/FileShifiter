/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.project.fileshifter.entity;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author ckans
 */
@Entity
@Table(name = "conversion_jobs")
public class ConversionJob {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(columnDefinition = "uuid", updatable = false, nullable = false)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_user"))
  private User user;

  @Column(nullable = false)
  private String originalFileName;

  @Column(nullable = false)
  private String originalFileType;

  private String convertedFileName;

  private String convertedFileType;

  @Column(nullable = false)
  private String originalFileS3Key;

  private String convertedFileS3Key;

  @Column(nullable = false)
  private String status;

  @JdbcTypeCode(SqlTypes.JSON)
  private Map<String, Object> conversionOptions;

  @Column(nullable = false)
  private OffsetDateTime createdAt = OffsetDateTime.now();

  private OffsetDateTime updatedAt;

  private OffsetDateTime expiresAt;

  private String errorMessage;

  // getters and setters
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getOriginalFileName() {
    return originalFileName;
  }

  public void setOriginalFileName(String originalFileName) {
    this.originalFileName = originalFileName;
  }

  public String getOriginalFileType() {
    return originalFileType;
  }

  public void setOriginalFileType(String originalFileType) {
    this.originalFileType = originalFileType;
  }

  public String getConvertedFileName() {
    return convertedFileName;
  }

  public void setConvertedFileName(String convertedFileName) {
    this.convertedFileName = convertedFileName;
  }

  public String getConvertedFileType() {
    return convertedFileType;
  }

  public void setConvertedFileType(String convertedFileType) {
    this.convertedFileType = convertedFileType;
  }

  public String getOriginalFileS3Key() {
    return originalFileS3Key;
  }

  public void setOriginalFileS3Key(String originalFileS3Key) {
    this.originalFileS3Key = originalFileS3Key;
  }

  public String getConvertedFileS3Key() {
    return convertedFileS3Key;
  }

  public void setConvertedFileS3Key(String convertedFileS3Key) {
    this.convertedFileS3Key = convertedFileS3Key;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Map<String, Object> getConversionOptions() {
    return conversionOptions;
  }

  public void setConversionOptions(Map<String, Object> conversionOptions) {
    this.conversionOptions = conversionOptions;
  }

  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public OffsetDateTime getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(OffsetDateTime expiresAt) {
    this.expiresAt = expiresAt;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

}
