package com.scmc.domain.builder;

import com.scmc.domain.dto.DecryptResponse;
import com.scmc.domain.dto.audit.AuditStep;

public class DecryptResponseBuilder {

  private final DecryptResponse decryptResponse;

  public DecryptResponseBuilder() {
    this.decryptResponse = new DecryptResponse();
  }

  public DecryptResponseBuilder setEncryptedMessage(String encryptedMessage) {
    this.decryptResponse.setEncryptedMessage(encryptedMessage);
    return this;
  }

  public DecryptResponseBuilder setPermutedMessage(String permutedText) {
    this.decryptResponse.setPermutedMessage(permutedText);
    return this;
  }

  public DecryptResponseBuilder setDecryptedMessage(String decryptedMessage) {
    this.decryptResponse.setDecryptedMessage(decryptedMessage);
    return this;
  }

  public DecryptResponseBuilder setBlockSize(Integer blockSize) {
    this.decryptResponse.setBlockSize(blockSize);
    return this;
  }

  public DecryptResponseBuilder setPermutation(java.util.List<Integer> permutation) {
    this.decryptResponse.setPermutation(permutation);
    return this;
  }

  public DecryptResponseBuilder setShift(Integer shift) {
    this.decryptResponse.setShift(shift);
    return this;
  }

  public DecryptResponseBuilder setAudit(java.util.List<AuditStep> audit) {
    this.decryptResponse.setAudit(audit);
    return this;
  }

  public DecryptResponse build() {
    return this.decryptResponse;
  }


}
