package com.scmc.service.audit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.scmc.domain.dto.audit.AuditStep;
import com.scmc.domain.dto.audit.AuditType;
import com.scmc.domain.dto.constants.CipherConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PaddingAuditServiceTest {

  private PaddingAuditService paddingAuditService;

  @Mock
  private AuditStepCounterService counterService;

  @BeforeEach
  public void setUp() {
    paddingAuditService = new PaddingAuditService();
  }

  @Test
  public void shouldCreateEncryptAuditStep() {

    String originalMessage = "Hola";
    String paddedMessage = "Hola" + com.scmc.domain.dto.constants.CipherConstants.PADDING_CHARACTER;

    when(counterService.incrementStepCounter()).thenReturn(1);

    AuditStep result = paddingAuditService.createEncryptAuditStep(
        counterService,
        originalMessage,
        paddedMessage
    );

    assertEquals(1, result.stepNumber());
    assertEquals(AuditType.RELLENO, result.title());

    assertEquals(
        String.format(
            "Se completó el último bloque utilizando el carácter de relleno '%c' cuando fue necesario.",
            CipherConstants.PADDING_CHARACTER
        ),
        result.description()
    );


    assertEquals(originalMessage, result.input());
    assertEquals(paddedMessage, result.output());

    verify(counterService).incrementStepCounter();
  }

  @Test
  public void shouldCreateDecryptAuditStep() {

    String paddedMessage = "Hola" + CipherConstants.PADDING_CHARACTER;
    String originalMessage = "Hola";

    when(counterService.incrementStepCounter()).thenReturn(2);

    AuditStep result = paddingAuditService.createDecryptAuditStep(
        counterService,
        paddedMessage,
        originalMessage
    );

    assertEquals(2, result.stepNumber());
    assertEquals(AuditType.RELLENO, result.title());

    assertEquals(
        String.format(
            "Se ha eliminado el relleno del mensaje utilizando el carácter '%c'.",
            CipherConstants.PADDING_CHARACTER
        ),
        result.description()
    );

    assertEquals(paddedMessage, result.input());
    assertEquals(originalMessage, result.output());

    verify(counterService).incrementStepCounter();
  }
}
