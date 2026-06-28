package com.scmc.dto;

import java.util.List;

public class EncryptResponse {

  private String originalMessage;

  private String paddedMessage;

  private String permutedMessage;

  private String encryptedMessage;

  private List<AuditStep> audit;
}
