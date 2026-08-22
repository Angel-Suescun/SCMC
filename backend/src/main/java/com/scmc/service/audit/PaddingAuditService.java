package com.scmc.service.audit;

import com.scmc.domain.dto.audit.AuditStep;
import com.scmc.domain.dto.audit.AuditType;
import com.scmc.domain.dto.constants.CipherConstants;
import org.springframework.stereotype.Service;

@Service
public class PaddingAuditService{

  public AuditStep createEncryptAuditStep(
      AuditStepCounterService counterService,
      String originalMessage,
      String paddedMessage
  ) {
    return new AuditStep(
        counterService.incrementStepCounter(),
        AuditType.RELLENO,
        buildDescriptionForEncrypt(),
        originalMessage,
        paddedMessage
    );

  }

  public AuditStep createDecryptAuditStep(
      AuditStepCounterService counterService,
      String paddedMessage,
      String originalMessage
  ) {
    return new AuditStep(
        counterService.incrementStepCounter(),
        AuditType.RELLENO,
        buildDescriptionForDecrypt(),
        paddedMessage,
        originalMessage
    );

  }

  private String buildDescriptionForEncrypt() {
    return String.format(
        "Se completó el último bloque utilizando el carácter de relleno '%c' cuando fue necesario.",
        CipherConstants.PADDING_CHARACTER
    );
  }

  private String buildDescriptionForDecrypt() {
    return String.format(
        "Se ha eliminado el relleno del mensaje utilizando el carácter '%c'.",
        CipherConstants.PADDING_CHARACTER
    );
  }
}
