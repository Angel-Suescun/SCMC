package com.scmc.domain.builder;

import com.scmc.domain.dto.DecryptResponse;
import com.scmc.domain.dto.audit.AuditStep;

public class DecryptResponseBuilder {

  private String encryptedMessage;
  private String permutedMessage;
  private String paddedMessage;
  private String decryptedMessage;
  private Integer blockSize;
  private java.util.List<Integer> permutation;
  private Integer shift;
  private java.util.List<AuditStep> audit;

  public DecryptResponseBuilder setEncryptedMessage(String encryptedMessage) {
    this.encryptedMessage = encryptedMessage;
    return this;
  }

  public DecryptResponseBuilder setPermutedMessage(String permutedText) {
    this.permutedMessage = permutedText;
    return this;
  }

  public DecryptResponseBuilder setPaddedMessage(String paddedMessage) {
    this.paddedMessage = paddedMessage;
    return this;
  }

  public String getPaddedMessage() {
    return paddedMessage;
  }

  public DecryptResponseBuilder setDecryptedMessage(String decryptedMessage) {
    this.decryptedMessage = decryptedMessage;
    return this;
  }

  public DecryptResponseBuilder setBlockSize(Integer blockSize) {
    this.blockSize = blockSize;
    return this;
  }

  public DecryptResponseBuilder setPermutation(java.util.List<Integer> permutation) {
    this.permutation = permutation;
    return this;
  }

  public DecryptResponseBuilder setShift(Integer shift) {
    this.shift = shift;
    return this;
  }

  public DecryptResponseBuilder setAudit(java.util.List<AuditStep> audit) {
    this.audit = audit;
    return this;
  }

  public DecryptResponse build() {

    return new DecryptResponse(
        encryptedMessage,
        permutedMessage,
        paddedMessage,
        decryptedMessage,
        blockSize,
        permutation,
        shift,
        audit
    );
  }


}
