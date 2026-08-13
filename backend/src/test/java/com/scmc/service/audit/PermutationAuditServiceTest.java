package com.scmc.service.audit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.scmc.domain.dto.audit.AuditStep;
import com.scmc.domain.dto.audit.AuditType;
import com.scmc.domain.dto.constants.CipherConstants;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PermutationAuditServiceTest {

  private PermutationAuditService permutationAuditService;

  @Mock
  private AuditStepCounterService counterService;

  @BeforeEach
  public void setUp() {
    permutationAuditService = new PermutationAuditService();
  }

  @Test
  public void shouldCreateEncryptAuditStep() {


    String paddedMessage = "Hola" + CipherConstants.PADDING_CHARACTER;
    String permutedMessage = "oHla" + CipherConstants.PADDING_CHARACTER;

    Integer blockSize = 4;
    List<Integer> permutation = List.of(1, 3, 2, 0);

    when(counterService.incrementStepCounter()).thenReturn(1);

    AuditStep result = permutationAuditService.createEncryptAuditStep(
        counterService,
        paddedMessage,
        permutedMessage,
        blockSize,
        permutation
    );

    assertEquals(1, result.getStepNumber());
    assertEquals(AuditType.PERMUTACION, result.getTitle());

    assertEquals(
        "Se ha aplicado una permutación de tamaño de bloque 4 al mensaje,"
            + " utilizando la siguiente permutación: [2, 4, 3, 1].",
        result.getDescription()
    );

    assertEquals(paddedMessage, result.getInput());
    assertEquals(permutedMessage, result.getOutput());

    verify(counterService).incrementStepCounter();
  }

  @Test
  public void shouldCreateDecryptAuditStep() {

    String permutedMessage = "oHla" + CipherConstants.PADDING_CHARACTER;
    String paddedMessage = "Hola" + CipherConstants.PADDING_CHARACTER;

    Integer blockSize = 4;
    List<Integer> permutation = List.of(1, 3, 2, 0);

    when(counterService.incrementStepCounter()).thenReturn(2);

    AuditStep result = permutationAuditService.createDecryptAuditStep(
        counterService,
        permutedMessage,
        paddedMessage,
        blockSize,
        permutation
    );

    assertEquals(2, result.getStepNumber());
    assertEquals(AuditType.DESPERMUTACION, result.getTitle());

    assertEquals(
        "Se dividió el mensaje en bloques de 4 caracteres"
            + " y se aplicó la permutación inversa correspondiente [2, 4, 3, 1] a cada bloque.",
        result.getDescription()
    );

    assertEquals(permutedMessage, result.getInput());
    assertEquals(paddedMessage, result.getOutput());

  }

}
