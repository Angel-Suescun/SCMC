package com.scmc.service.audit;

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

    return new AuditStep(
        counterService.incrementStepCounter(),
        AuditType.DESPLAZAMIENTO_MODULAR,
        buildDescriptionForEncrypt(shift),
        permutedMessage,
        encryptedMessage
    );

  }

  public AuditStep createDecryptAuditStep(
    AuditStepCounterService counterService,
    String encryptedMessage,
    String decryptedMessage,
    Integer shift
  ) {

    return new AuditStep(
        counterService.incrementStepCounter(),
        AuditType.DESPLAZAMIENTO_MODULAR_INVERSO,
        buildDescriptionForDecrypt(shift),
        encryptedMessage,
        decryptedMessage
    );
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

