package com.scmc.service.audit;

import com.scmc.domain.builder.AuditStepBuilder;
import com.scmc.domain.dto.audit.AuditStep;
import com.scmc.domain.dto.audit.AuditType;
import com.scmc.domain.dto.constants.CipherConstants;
import org.springframework.stereotype.Service;

@Service
public class PaddingAuditService{

  public AuditStep createAuditStep(
      AuditStepCounterService counterService,
      String originalMessage,
      String paddedMessage
  ) {
    return new AuditStepBuilder()
        .setStepNumber(counterService.incrementStepCounter())
        .setTitle(AuditType.RELLENO)
        .setDescription(buildDescription())
        .setInput(originalMessage)
        .setOutput(paddedMessage)
        .build();
  }

  private String buildDescription() {
    return String.format(
        "Se ha aplicado un relleno al mensaje original utilizando el carácter '%c'.",
        CipherConstants.PADDING_CHARACTER
    );
  }
}
