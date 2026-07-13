package com.scmc.service.audit;

import com.scmc.domain.dto.audit.AuditStep;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DecryptAuditService {

  private final PaddingAuditService paddingAuditService;

  private final PermutationAuditService permutationAuditService;

  private final ModularAuditService modularAuditService;

  public DecryptAuditService(
      PaddingAuditService paddingAuditService,
      PermutationAuditService permutationAuditService,
      ModularAuditService modularAuditService
  ) {
    this.paddingAuditService = paddingAuditService;
    this.permutationAuditService = permutationAuditService;
    this.modularAuditService = modularAuditService;
  }

  public List<AuditStep> buildAuditSteps(
      String encryptedMessage,
      String permutedMessage,
      String paddedMessage,
      String decryptedMessage,
      Integer blockSize,
      List<Integer> permutation,
      Integer shift
  ) {

    AuditStepCounterService counter = new AuditStepCounterService();

    List<AuditStep> auditSteps = new ArrayList<>();

    auditSteps.add(
        modularAuditService.createDecryptAuditStep(
            counter,
            encryptedMessage,
            permutedMessage,
            shift
        )
    );

    auditSteps.add(
        permutationAuditService.createDecryptAuditStep(
            counter,
            permutedMessage,
            paddedMessage,
            blockSize,
            permutation
        ));

    auditSteps.add(
        paddingAuditService.createDecryptAuditStep(
            counter,
            paddedMessage,
            decryptedMessage
        )
    );

    return auditSteps;
  }


}
