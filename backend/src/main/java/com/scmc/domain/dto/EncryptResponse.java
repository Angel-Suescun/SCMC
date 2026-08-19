package com.scmc.domain.dto;

import com.scmc.domain.dto.audit.AuditStep;
import java.util.List;

public record EncryptResponse (

    String originalMessage,

    String paddedMessage,

    String permutedMessage,

    String encryptedMessage,

    Integer blockSize,

    List<Integer> permutation,

    Integer shift,

    List<AuditStep> audit
){

  }
