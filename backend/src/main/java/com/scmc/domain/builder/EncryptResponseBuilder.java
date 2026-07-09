package com.scmc.domain.builder;

import com.scmc.domain.dto.audit.AuditStep;
import java.util.List;
import com.scmc.domain.dto.EncryptResponse;

public class EncryptResponseBuilder {

  private String originalMessage;
  private String paddedMessage;
  private String permutedMessage;
  private String encryptedMessage;
  private Integer blockSize;
  private List<Integer> permutation;
  private Integer shift;
  private List<AuditStep> audit;

  public EncryptResponseBuilder setOriginalMessage(String originalMessage) {
    this.originalMessage = originalMessage;
    return this;
  }

  public EncryptResponseBuilder setPaddedMessage(String paddedMessage) {
    this.paddedMessage = paddedMessage;
    return this;
  }

  public EncryptResponseBuilder setPermutedMessage(String permutedMessage) {
    this.permutedMessage = permutedMessage;
    return this;
  }

  public EncryptResponseBuilder setEncryptedMessage(String encryptedMessage) {
    this.encryptedMessage = encryptedMessage;
    return this;
  }

  public EncryptResponseBuilder setBlockSize(Integer blockSize) {
    this.blockSize = blockSize;
    return this;
  }

  public EncryptResponseBuilder setPermutation(List<Integer> permutation) {
    this.permutation = permutation;
    return this;
  }

  public EncryptResponseBuilder setShift(Integer shift) {
    this.shift = shift;
    return this;
  }

  public EncryptResponseBuilder setAudit(List<AuditStep> audit) {
    this.audit = audit;
    return this;
  }

  public EncryptResponse build() {
    return new EncryptResponse(
        originalMessage,
        paddedMessage,
        permutedMessage,
        encryptedMessage,
        blockSize,
        permutation,
        shift,
        audit
    );
  }
}
