package com.scmc.domain.dto;

import com.scmc.domain.dto.audit.AuditStep;
import java.util.List;

public record DecryptResponse (

    String encryptedMessage,

    String permutedMessage,

    String paddedMessage,

    String decryptedMessage,

    Integer blockSize,

    List<Integer> permutation,

    Integer shift,

    List<AuditStep> audit
){


}
