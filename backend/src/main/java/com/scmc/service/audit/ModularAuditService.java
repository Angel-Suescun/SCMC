package com.scmc.service.audit;

import com.scmc.domain.builder.AuditStepBuilder;
import com.scmc.domain.dto.audit.AuditStep;
import com.scmc.domain.dto.audit.AuditType;
import org.springframework.stereotype.Service;

@Service
public class ModularAuditService {

  public AuditStep createEncryptAuditStep(
    AuditStepCounterService counterService,
    String permutedMessage,
    String encryptedMessage,
    Integer shift
  ) {

    return new AuditStepBuilder()
        .setStepNumber(counterService.incrementStepCounter())
        .setTitle(AuditType.DESPLAZAMIENTO_MODULAR)
        .setDescription(buildDescriptionForEncrypt(shift))
        .setInput(permutedMessage)
        .setOutput(encryptedMessage)
        .build();


  }

  public AuditStep createDecryptAuditStep(
    AuditStepCounterService counterService,
    String encryptedMessage,
    String decryptedMessage,
    Integer shift
  ) {

    return new AuditStepBuilder()
        .setStepNumber(counterService.incrementStepCounter())
        .setTitle(AuditType.DESPLAZAMIENTO_MODULAR_INVERSO)
        .setDescription(buildDescriptionForDecrypt(shift))
        .setInput(encryptedMessage)
        .setOutput(decryptedMessage)
        .build();
  }

  private String buildDescriptionForEncrypt(Integer shift) {
    return String.format(
        "Se ha aplicado un desplazamiento modular de %d posiciones a cada carácter del "
            + "mensaje permutado.",
        shift
    );
  }

  private String buildDescriptionForDecrypt(Integer shift) {
    return String.format(
        "Se aplicó el desplazamiento modular inverso de %d posiciones "
            + "a cada carácter del mensaje.",
        shift
    );
  }
}

