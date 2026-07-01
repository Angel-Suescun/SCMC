package com.scmc.domain.builder;

import com.scmc.domain.dto.AuditStep;
import java.util.List;
import com.scmc.domain.dto.EncryptResponse;

public class EncryptResponseBuilder {

  private final EncryptResponse encryptResponse;

  public EncryptResponseBuilder() {
    this.encryptResponse = new EncryptResponse();
  }

  public EncryptResponseBuilder setOriginalMessage(String originalMessage) {
    this.encryptResponse.setOriginalMessage(originalMessage);
    return this;
  }

  public EncryptResponseBuilder setPaddedMessage(String paddedMessage) {
    this.encryptResponse.setPaddedMessage(paddedMessage);
    return this;
  }

  public EncryptResponseBuilder setPermutedMessage(String permutedMessage) {
    this.encryptResponse.setPermutedMessage(permutedMessage);
    return this;
  }

  public EncryptResponseBuilder setEncryptedMessage(String encryptedMessage) {
    this.encryptResponse.setEncryptedMessage(encryptedMessage);
    return this;
  }

  public EncryptResponseBuilder setBlockSize(Integer blockSize) {
    this.encryptResponse.setBlockSize(blockSize);
    return this;
  }

  public EncryptResponseBuilder setPermutation(List<Integer> permutation) {
    this.encryptResponse.setPermutation(permutation);
    return this;
  }

  public EncryptResponseBuilder setShift(Integer shift) {
    this.encryptResponse.setShift(shift);
    return this;
  }

  public EncryptResponseBuilder setAudit(List<AuditStep> audit) {
    this.encryptResponse.setAudit(audit);
    return this;
  }

  public EncryptResponse build() {
    return this.encryptResponse;
  }
}
