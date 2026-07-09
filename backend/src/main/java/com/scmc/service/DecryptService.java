package com.scmc.service;

import com.scmc.domain.builder.DecryptResponseBuilder;
import com.scmc.domain.dto.DecryptRequest;
import com.scmc.domain.dto.DecryptResponse;
import com.scmc.service.algorithm.ModularDecryptService;
import com.scmc.service.algorithm.PermutationDecryptService;
import org.springframework.stereotype.Service;

@Service
public class DecryptService {

  private final ValidationService validationService;

  private final ShiftService shiftService;

  private final ModularDecryptService modularDecryptService;

  private final PermutationDecryptService permutationDecryptService;

  public DecryptService(
      ValidationService validationService,
      ShiftService shiftService,
      ModularDecryptService modularDecryptService,
      PermutationDecryptService permutationDecryptService) {
    this.validationService = validationService;
    this.shiftService = shiftService;
    this.modularDecryptService = modularDecryptService;
    this.permutationDecryptService = permutationDecryptService;
  }

  public DecryptResponse decrypt(DecryptRequest request) {

    validationService.validateDecryptRequest(request);

    Integer normalizedShift = shiftService.normalizeShift(request.shift());

    String permutedMessage = modularDecryptService.decrypt(
        request.encryptedMessage(),
        normalizedShift
    );

    String decryptedMessage = permutationDecryptService.decrypt(
        permutedMessage,
        request.permutation(),
        request.blockSize()
    );

    return new DecryptResponseBuilder()
        .setEncryptedMessage(request.encryptedMessage())
        .setPermutedMessage(permutedMessage)
        .setDecryptedMessage(decryptedMessage)
        .setBlockSize(request.blockSize())
        .setPermutation(request.permutation())
        .setShift(normalizedShift)
        .build();
  }
}
