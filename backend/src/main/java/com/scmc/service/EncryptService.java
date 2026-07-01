package com.scmc.service;

import com.scmc.domain.builder.EncryptResponseBuilder;
import com.scmc.domain.dto.EncryptRequest;
import com.scmc.domain.dto.EncryptResponse;
import com.scmc.service.algorithm.ModularEncryptService;
import com.scmc.service.algorithm.PermutationEncryptService;
import org.springframework.stereotype.Service;

@Service
public class EncryptService {

  private final ValidationService validationService;

  private final ShiftService shiftService;

  private final PermutationEncryptService permutationEncryptService;

  private final ModularEncryptService modularEncryptService;

  public EncryptService(
      ValidationService validationService,
      ShiftService shiftService,
      PermutationEncryptService permutationEncryptService,
      ModularEncryptService modularEncryptService) {

    this.validationService = validationService;
    this.shiftService = shiftService;
    this.permutationEncryptService = permutationEncryptService;
    this.modularEncryptService = modularEncryptService;
  }

  public EncryptResponse encrypt(EncryptRequest request){

    validationService.validateEncryptRequest(request);

    Integer normalizedShift = shiftService.normalizeShift(request.shift());

    String permutedMessage = permutationEncryptService.encrypt(
        request.message(),
        request.permutation(),
        request.blockSize()
    );

    String encryptedMessage = modularEncryptService.encrypt(
        permutedMessage,
        normalizedShift
    );

    return new EncryptResponseBuilder()
        .setOriginalMessage(request.message())
        .setPaddedMessage(permutedMessage)
        .setPermutedMessage(permutedMessage)
        .setEncryptedMessage(encryptedMessage)
        .setBlockSize(request.blockSize())
        .setPermutation(request.permutation())
        .setShift(normalizedShift)
        .build();
  }
}
