package com.scmc.service.audit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.scmc.domain.dto.audit.AuditStep;
import com.scmc.domain.dto.audit.AuditType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ModularAuditServiceTest {

  private ModularAuditService modularAuditService;

  @Mock
  private AuditStepCounterService counterService;

  @BeforeEach
  public void setUp() {
    modularAuditService = new ModularAuditService();
  }

  @Test
  public void shouldCreateEncryptAuditStep() {

    String permutedMessage = "oHla";
    String encryptedMessage = "Krod";
    Integer shift = 3;

    when(counterService.incrementStepCounter()).thenReturn(1);

    AuditStep result = modularAuditService.createEncryptAuditStep(
        counterService,
        permutedMessage,
        encryptedMessage,
        shift
    );

    assertEquals(1, result.getStepNumber());
    assertEquals(AuditType.DESPLAZAMIENTO_MODULAR, result.getTitle());


    assertEquals(
        "Se ha aplicado un desplazamiento modular de 3 posiciones "
            + "a cada carácter del mensaje permutado.",
        result.getDescription()
    );

    assertEquals(permutedMessage, result.getInput());
    assertEquals(encryptedMessage, result.getOutput());

  }

  @Test
  public void shouldCreateDecryptAuditStep() {

    String encryptedMessage = "Krod";
    String decryptedMessage = "oHla";
    Integer shift = 3;

    when(counterService.incrementStepCounter()).thenReturn(2);

    AuditStep result = modularAuditService.createDecryptAuditStep(
        counterService,
        encryptedMessage,
        decryptedMessage,
        shift
    );

    assertEquals(2, result.getStepNumber());
    assertEquals(AuditType.DESPLAZAMIENTO_MODULAR_INVERSO, result.getTitle());

    assertEquals(
        "Se aplicó el desplazamiento modular inverso de 3 posiciones "
            + "a cada carácter del mensaje.",
        result.getDescription()
    );

    assertEquals(encryptedMessage, result.getInput());
    assertEquals(decryptedMessage, result.getOutput());

  }
}
