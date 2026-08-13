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
public class DecryptAuditServiceTest {

  @Mock
  private PaddingAuditService paddingAuditService;

  @Mock
  private PermutationAuditService permutationAuditService;

  @Mock
  private ModularAuditService modularAuditService;

  @Mock
  private AuditStep paddingStep;

  @Mock
  private AuditStep permutationStep;

  @Mock
  private AuditStep modularStep;

  @InjectMocks
  private DecryptAuditService decryptAuditService;

  @Test
  public void shouldBuildDecryptAuditSteps() {

    String encryptedMessage = "Krod";
    String permutedMessage = "oHla";
    String paddedMessage = "Hola" + CipherConstants.PADDING_CHARACTER;
    String decryptedMessage = "Hola";

    Integer blockSize = 4;
    List<Integer> permutation = List.of(1, 3, 2, 0);
    Integer shift = 3;

    when(modularAuditService.createDecryptAuditStep(
        any(AuditStepCounterService.class),
        eq(encryptedMessage),
        eq(permutedMessage),
        eq(shift)
    )).thenReturn(modularStep);

    when(permutationAuditService.createDecryptAuditStep(
        any(AuditStepCounterService.class),
        eq(permutedMessage),
        eq(paddedMessage),
        eq(blockSize),
        eq(permutation)
    )).thenReturn(permutationStep);

    when(paddingAuditService.createDecryptAuditStep(
        any(AuditStepCounterService.class),
        eq(paddedMessage),
        eq(decryptedMessage)
    )).thenReturn(paddingStep);

    List<AuditStep> auditSteps = decryptAuditService.buildAuditSteps(
        encryptedMessage,
        permutedMessage,
        paddedMessage,
        decryptedMessage,
        blockSize,
        permutation,
        shift
    );

    assertEquals(3, auditSteps.size());
    assertSame(modularStep, auditSteps.get(0));
    assertSame(permutationStep, auditSteps.get(1));
    assertSame(paddingStep, auditSteps.get(2));
  }

  @Test
  public void shouldCallAuditServicesInCorrectOrder() {

    String encryptedMessage = "Krod";
    String permutedMessage = "oHla";
    String paddedMessage = "Hola" + CipherConstants.PADDING_CHARACTER;
    String decryptedMessage = "Hola";

    Integer blockSize = 4;
    List<Integer> permutation = List.of(1, 3, 2, 0);
    Integer shift = 3;

    when(modularAuditService.createDecryptAuditStep(
        any(AuditStepCounterService.class),
        anyString(),
        anyString(),
        anyInt()
    )).thenReturn(modularStep);

    when(permutationAuditService.createDecryptAuditStep(
        any(AuditStepCounterService.class),
        anyString(),
        anyString(),
        anyInt(),
        anyList()
    )).thenReturn(permutationStep);

    when(paddingAuditService.createDecryptAuditStep(
        any(AuditStepCounterService.class),
        anyString(),
        anyString()
    )).thenReturn(paddingStep);

    decryptAuditService.buildAuditSteps(
        encryptedMessage,
        permutedMessage,
        paddedMessage,
        decryptedMessage,
        blockSize,
        permutation,
        shift
    );

    InOrder inOrder = inOrder(
        modularAuditService,
        permutationAuditService,
        paddingAuditService
    );

    inOrder.verify(modularAuditService).createDecryptAuditStep(
        any(AuditStepCounterService.class),
        eq(encryptedMessage),
        eq(permutedMessage),
        eq(shift)
    );

    inOrder.verify(permutationAuditService).createDecryptAuditStep(
        any(AuditStepCounterService.class),
        eq(permutedMessage),
        eq(paddedMessage),
        eq(blockSize),
        eq(permutation)
    );

    inOrder.verify(paddingAuditService).createDecryptAuditStep(
        any(AuditStepCounterService.class),
        eq(paddedMessage),
        eq(decryptedMessage)
    );
  }
}
