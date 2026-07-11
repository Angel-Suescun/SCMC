package com.scmc.service;

import com.scmc.domain.builder.EncryptResponseBuilder;
import com.scmc.domain.dto.EncryptRequest;
import com.scmc.domain.dto.EncryptResponse;
import com.scmc.domain.dto.audit.AuditStep;
import com.scmc.service.algorithm.ModularEncryptService;
import com.scmc.service.algorithm.PaddingEncryptService;
import com.scmc.service.algorithm.PermutationEncryptService;
import com.scmc.service.audit.EncryptAuditService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EncryptService {

  private final ValidationService validationService;

  private final ShiftService shiftService;

  private final PaddingEncryptService paddingEncryptService;

  private final PermutationEncryptService permutationEncryptService;

  private final ModularEncryptService modularEncryptService;

  private final EncryptAuditService encryptAuditService;

  public EncryptService(
    ValidationService validationService,
    ShiftService shiftService,
    PermutationEncryptService permutationEncryptService,
    ModularEncryptService modularEncryptService,
    EncryptAuditService encryptAuditService,
    PaddingEncryptService paddingEncryptService
  ) {

    this.validationService = validationService;
    this.shiftService = shiftService;
    this.permutationEncryptService = permutationEncryptService;
    this.modularEncryptService = modularEncryptService;
    this.encryptAuditService = encryptAuditService;
    this.paddingEncryptService = paddingEncryptService;
  }

  public EncryptResponse encrypt(EncryptRequest request){

    validationService.validateEncryptRequest(request);

    Integer normalizedShift = shiftService.normalizeShift(request.shift());

    String paddedMessage = paddingEncryptService.encrypt(
        request.message(),
        request.blockSize()
    );

    String permutedMessage = permutationEncryptService.encrypt(
        paddedMessage,
        request.permutation(),
        request.blockSize()
    );

    String encryptedMessage = modularEncryptService.encrypt(
        permutedMessage,
        normalizedShift
    );

    List<AuditStep> audit = encryptAuditService.buildAuditSteps(
        request.message(),
        paddedMessage,
        permutedMessage,
        encryptedMessage,
        request.blockSize(),
        request.permutation(),
        normalizedShift
    );

    return new EncryptResponseBuilder()
        .setOriginalMessage(request.message())
        .setPermutedMessage(permutedMessage)
        .setEncryptedMessage(encryptedMessage)
        .setBlockSize(request.blockSize())
        .setPermutation(request.permutation())
        .setShift(normalizedShift)
        .build();
  }
}

