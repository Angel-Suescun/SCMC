package com.scmc.service.audit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

import com.scmc.domain.dto.audit.AuditStep;
import com.scmc.domain.dto.constants.CipherConstants;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EncryptAuditServiceTest {

  @Mock
  private PaddingAuditService paddingAuditService;

  @Mock
  private PermutationAuditService permutationAuditService;

  @Mock
  private ModularAuditService modularAuditService;

  @InjectMocks
  private EncryptAuditService encryptAuditService;

  @Mock
  private AuditStep paddingStep;

  @Mock
  private AuditStep permutationStep;

  @Mock
  private AuditStep modularStep;

  @Test
  public void shouldBuildEncryptAuditSteps() {

    String originalMessage = "Hola";
    String paddedMessage = "Hola" + CipherConstants.PADDING_CHARACTER;
    String permutedMessage = "oHla" + CipherConstants.PADDING_CHARACTER;
    String encryptedMessage = "Krod" + CipherConstants.PADDING_CHARACTER;

    Integer blockSize = 4;
    List<Integer> permutation = List.of(1, 3, 2, 0);
    Integer shift = 3;

    when(paddingAuditService.createEncryptAuditStep(
      any(AuditStepCounterService.class),
      eq(originalMessage),
      eq(paddedMessage)
    )).thenReturn(paddingStep);

    when(permutationAuditService.createEncryptAuditStep(
      any(AuditStepCounterService.class),
      eq(paddedMessage),
      eq(permutedMessage),
      eq(blockSize),
      eq(permutation)
    )).thenReturn(permutationStep);

    when(modularAuditService.createEncryptAuditStep(
      any(AuditStepCounterService.class),
      eq(permutedMessage),
      eq(encryptedMessage),
      eq(shift)
    )).thenReturn(modularStep);

    List<AuditStep> result = encryptAuditService.buildAuditSteps(
      originalMessage,
      paddedMessage,
      permutedMessage,
      encryptedMessage,
      blockSize,
      permutation,
      shift
    );

    assertEquals(3, result.size());
    assertSame(paddingStep, result.get(0));
    assertSame(permutationStep, result.get(1));
    assertSame(modularStep, result.get(2));
  }

  @Test
  public void shouldCallAuditServicesInCorrectOrder() {

    String originalMessage = "Hola";
    String paddedMessage = "Hola" + CipherConstants.PADDING_CHARACTER;
    String permutedMessage = "oHla" + CipherConstants.PADDING_CHARACTER;
    String encryptedMessage = "Krod" + CipherConstants.PADDING_CHARACTER;

    Integer blockSize = 4;
    List<Integer> permutation = List.of(1, 3, 2, 0);
    Integer shift = 5;

    when(paddingAuditService.createEncryptAuditStep(
      any(AuditStepCounterService.class),
      anyString(),
      anyString()
    )).thenReturn(paddingStep);

    when(permutationAuditService.createEncryptAuditStep(
      any(AuditStepCounterService.class),
      anyString(),
      anyString(),
      anyInt(),
      anyList()
    )).thenReturn(permutationStep);

    when(modularAuditService.createEncryptAuditStep(
      any(AuditStepCounterService.class),
      anyString(),
      anyString(),
      anyInt()
    )).thenReturn(modularStep);

    encryptAuditService.buildAuditSteps(
      originalMessage,
      paddedMessage,
      permutedMessage,
      encryptedMessage,
      blockSize,
      permutation,
      shift
    );

    InOrder inOrder = inOrder(
      paddingAuditService,
      permutationAuditService,
      modularAuditService
    );

    inOrder.verify(paddingAuditService).createEncryptAuditStep(
      any(AuditStepCounterService.class),
      eq(originalMessage),
      eq(paddedMessage)
    );

    inOrder.verify(permutationAuditService).createEncryptAuditStep(
      any(AuditStepCounterService.class),
      eq(paddedMessage),
      eq(permutedMessage),
      eq(blockSize),
      eq(permutation)
    );

    inOrder.verify(modularAuditService).createEncryptAuditStep(
      any(AuditStepCounterService.class),
      eq(permutedMessage),
      eq(encryptedMessage),
      eq(shift)
    );

  }
}
