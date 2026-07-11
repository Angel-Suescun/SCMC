package com.scmc.service.audit;

import com.scmc.domain.dto.audit.AuditStep;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EncryptAuditService {

  private final PaddingAuditService paddingAuditService;

  private final PermutationAuditService permutationAuditService;

  private final ModularAuditService modularAuditService;

  public EncryptAuditService(
      PaddingAuditService paddingAuditService,
      PermutationAuditService permutationAuditService,
      ModularAuditService modularAuditService
  ) {

    this.paddingAuditService = paddingAuditService;
    this.permutationAuditService = permutationAuditService;
    this.modularAuditService = modularAuditService;

  }

  public List<AuditStep> buildAuditSteps(
    String originalMessage,
    String paddedMessage,
    String permutedMessage,
    String encryptedMessage,
    Integer blockSize,
    List<Integer> permutation,
    Integer shift
    ){

    AuditStepCounterService counter = new AuditStepCounterService();

    List<AuditStep> auditSteps = new ArrayList<>();

    auditSteps.add(paddingAuditService.createAuditStep(counter, originalMessage, paddedMessage));

    auditSteps.add(permutationAuditService.createAuditStep(
        counter,
        paddedMessage,
        permutedMessage,
        blockSize,
        permutation
    ));

    auditSteps.add(
        modularAuditService.createAuditStep(
            counter,
            permutedMessage,
            encryptedMessage,
            shift
    ));

    return auditSteps;
}
}
