package com.scmc.domain.dto;

import com.scmc.domain.dto.audit.AuditStep;
import java.util.List;

public class DecryptResponse {

  private String encryptedMessage;

  private String permutedMessage;

  private String paddedMessage;

  private String decryptedMessage;

  private Integer blockSize;

  private List<Integer> permutation;

  private Integer shift;

  private List<AuditStep> audit;

  public DecryptResponse() {
  }

  public DecryptResponse(
    String encryptedMessage,
    String permutedMessage,
    String paddedMessage,
    String decryptedMessage,
    Integer blockSize,
    List<Integer> permutation,
    Integer shift,
    List<AuditStep> audit
  ){
    this.encryptedMessage = encryptedMessage;
    this.permutedMessage = permutedMessage;
    this.paddedMessage = paddedMessage;
    this.decryptedMessage = decryptedMessage;
    this.blockSize = blockSize;
    this.permutation = permutation;
    this.shift = shift;
    this.audit = audit;
  }

  public String getEncryptedMessage() {
    return encryptedMessage;
  }

  public void setEncryptedMessage(String encryptedMessage) {
    this.encryptedMessage = encryptedMessage;
  }

  public String getPermutedMessage() {
    return permutedMessage;
  }

  public void setPermutedMessage(String modularDecryptedMessage) {
    this.permutedMessage = modularDecryptedMessage;
  }

  public void setPaddedMessage(String paddedMessage) {
    this.paddedMessage = paddedMessage;
  }

  public String getPaddedMessage() {
    return paddedMessage;
  }

  public String getDecryptedMessage() {
    return decryptedMessage;
  }

  public void setDecryptedMessage(String decryptedMessage) {
    this.decryptedMessage = decryptedMessage;
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
