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
}
