package com.scmc.service;

import com.scmc.domain.builder.DecryptResponseBuilder;
import com.scmc.domain.dto.DecryptRequest;
import com.scmc.domain.dto.DecryptResponse;
import com.scmc.domain.dto.audit.AuditStep;
import com.scmc.service.algorithm.ModularDecryptService;
import com.scmc.service.algorithm.PaddingDecryptService;
import com.scmc.service.algorithm.PermutationDecryptService;
import com.scmc.service.audit.DecryptAuditService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DecryptService {

  private final ValidationService validationService;

  private final ShiftService shiftService;

  private final ModularDecryptService modularDecryptService;

  private final PermutationDecryptService permutationDecryptService;

  private final PaddingDecryptService paddingDecryptService;

  private final DecryptAuditService decryptAuditService;

  public DecryptService(
      ValidationService validationService,
      ShiftService shiftService,
      ModularDecryptService modularDecryptService,
      PermutationDecryptService permutationDecryptService,
      PaddingDecryptService paddingDecryptService,
      DecryptAuditService decryptAuditService
  ) {
    this.validationService = validationService;
    this.shiftService = shiftService;
    this.modularDecryptService = modularDecryptService;
    this.permutationDecryptService = permutationDecryptService;
    this.paddingDecryptService = paddingDecryptService;
    this.decryptAuditService = decryptAuditService;
  }

  public DecryptResponse decrypt(DecryptRequest request) {

    validationService.validateDecryptRequest(request);

    Integer normalizedShift = shiftService.normalizeShift(request.shift());

    String permutedMessage = modularDecryptService.decrypt(
        request.encryptedMessage(),
        normalizedShift
    );

    String paddedMessage = permutationDecryptService.decrypt(
        permutedMessage,
        request.permutation(),
        request.blockSize()
    );

    String decryptedMessage = paddingDecryptService.decrypt(paddedMessage);

    List<AuditStep> auditSteps = decryptAuditService.buildAuditSteps(
        request.encryptedMessage(),
        permutedMessage,
        paddedMessage,
        decryptedMessage,
        request.blockSize(),
        request.permutation(),
        normalizedShift
    );

    return new DecryptResponseBuilder()
        .setEncryptedMessage(request.encryptedMessage())
        .setPermutedMessage(permutedMessage)
        .setPaddedMessage(paddedMessage)
        .setDecryptedMessage(decryptedMessage)
        .setBlockSize(request.blockSize())
        .setPermutation(request.permutation())
        .setShift(normalizedShift)
        .setAudit(auditSteps)
        .build();
  }
}
