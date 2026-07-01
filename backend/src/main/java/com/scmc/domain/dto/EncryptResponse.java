package com.scmc.domain.dto;

import java.util.List;

public class EncryptResponse {

  private String originalMessage;

  private String paddedMessage;

  private String permutedMessage;

  private String encryptedMessage;

  private Integer blockSize;

  private List<Integer> permutation;

  private Integer shift;

  private List<AuditStep> audit;

  public EncryptResponse() {
  }

  public EncryptResponse(
    String originalMessage,
    String paddedMessage,
    String permutedMessage,
    String encryptedMessage,
    Integer blockSize,
    List<Integer> permutation,
    Integer shift,
    List<AuditStep> audit
  ){
    this.originalMessage = originalMessage;
    this.paddedMessage = paddedMessage;
    this.permutedMessage = permutedMessage;
    this.encryptedMessage = encryptedMessage;
    this.blockSize = blockSize;
    this.permutation = permutation;
    this.shift = shift;
    this.audit = audit;
  }

  public String getOriginalMessage() {
    return originalMessage;
  }

  public void setOriginalMessage(String originalMessage) {
    this.originalMessage = originalMessage;
  }

  public String getPaddedMessage() {
    return paddedMessage;
  }

  public void setPaddedMessage(String paddedMessage) {
    this.paddedMessage = paddedMessage;
  }

  public String getPermutedMessage() {
    return permutedMessage;
  }

  public void setPermutedMessage(String permutedMessage) {
    this.permutedMessage = permutedMessage;
  }

  public String getEncryptedMessage() {
    return encryptedMessage;
  }

  public void setEncryptedMessage(String encryptedMessage) {
    this.encryptedMessage = encryptedMessage;
  }

  public Integer getBlockSize() {
    return blockSize;
  }

  public void setBlockSize(Integer blockSize) {
    this.blockSize = blockSize;
  }

  public List<Integer> getPermutation() {
    return permutation;
  }

  public void setPermutation(List<Integer> permutation) {
    this.permutation = permutation;
  }

  public Integer getShift() {
    return shift;
  }

  public void setShift(Integer shift) {
    this.shift = shift;
  }

  public List<AuditStep> getAudit() {
    return audit;
  }

  public void setAudit(List<AuditStep> audit) {
    this.audit = audit;
  }
}
