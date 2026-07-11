package com.scmc.service.audit;

import com.scmc.domain.builder.AuditStepBuilder;
import com.scmc.domain.dto.audit.AuditStep;
import com.scmc.domain.dto.audit.AuditType;
import org.springframework.stereotype.Service;

@Service
public class ModularAuditService {

  public AuditStep createAuditStep(
    AuditStepCounterService counterService,
    String permutedMessage,
    String encryptedMessage,
    Integer shift
  ) {

    return new AuditStepBuilder()
        .setStepNumber(counterService.incrementStepCounter())
        .setTitle(AuditType.DESPLAZAMIENTO_MODULAR)
        .setDescription(buildDescription(shift))
        .setInput(permutedMessage)
        .setOutput(encryptedMessage)
        .build();


  }

  private String buildDescription(Integer shift) {
    return String.format(
        "Se ha aplicado un desplazamiento modular de %d posiciones a cada carácter del "
            + "mensaje permutado.",
        shift
    );
  }
}
