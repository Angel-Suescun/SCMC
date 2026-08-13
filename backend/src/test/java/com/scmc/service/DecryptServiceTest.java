package com.scmc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.scmc.domain.dto.DecryptRequest;
import com.scmc.domain.dto.DecryptResponse;
import com.scmc.domain.dto.audit.AuditStep;
import com.scmc.service.algorithm.ModularDecryptService;
import com.scmc.service.algorithm.PaddingDecryptService;
import com.scmc.service.algorithm.PermutationDecryptService;
import com.scmc.service.audit.DecryptAuditService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DecryptServiceTest {

  @Mock
  private ValidationService validationService;

  @Mock
  private ShiftService shiftService;

  @Mock
  private ModularDecryptService modularDecryptService;

  @Mock
  private PermutationDecryptService permutationDecryptService;

  @Mock
  private PaddingDecryptService paddingDecryptService;

  @Mock
  private DecryptAuditService decryptAuditService;

  @InjectMocks
  private DecryptService decryptService;

  @Test
  public void shouldDecryptMessageSuccessfully() {

    String encryptedMessage = "encryptedMessage";
    int blockSize = 3;
    List<Integer> permutation = List.of(2, 0, 1);
    int shift = 261;

    DecryptRequest request = new DecryptRequest(
        encryptedMessage,
        blockSize,
        permutation,
        shift
    );

    Integer normalizedShift = shift % 256;

    String permutedMessage = "permutedMessage";
    String paddedMessage = "paddedMessage";
    String decryptedMessage = "decryptedMessage";

    List<AuditStep> auditSteps = List.of();

    when(shiftService.normalizeShift(shift)).thenReturn(
        normalizedShift
    );

    when(modularDecryptService.decrypt(encryptedMessage, normalizedShift)).thenReturn(
        permutedMessage
    );

    when(permutationDecryptService.decrypt(
        permutedMessage,
        request.permutation(),
        request.blockSize())
    ).thenReturn(
        paddedMessage
    );
    when(paddingDecryptService.decrypt(paddedMessage)).thenReturn(decryptedMessage);
    when(decryptAuditService.buildAuditSteps(
        encryptedMessage,
        permutedMessage,
        paddedMessage,
        decryptedMessage,
        request.blockSize(),
        request.permutation(),
        normalizedShift)
    ).thenReturn(auditSteps);

    DecryptResponse response = decryptService.decrypt(request);

    assertEquals(encryptedMessage, response.getEncryptedMessage());
    assertEquals(permutedMessage, response.getPermutedMessage());
    assertEquals(paddedMessage, response.getPaddedMessage());
    assertEquals(decryptedMessage, response.getDecryptedMessage());
    assertEquals(request.blockSize(), response.getBlockSize());
    assertEquals(request.permutation(), response.getPermutation());
    assertEquals(normalizedShift, response.getShift());
    assertEquals(auditSteps, response.getAudit());

    verify(validationService).validateDecryptRequest(request);
  }

  @Test
  public void shouldExecuteDecryptStepsInCorrectOrder() {

    String encryptedMessage = "encryptedMessage";
    int blockSize = 3;
    List<Integer> permutation = List.of(2, 0, 1);
    int shift = 261;

    DecryptRequest request = new DecryptRequest(
        encryptedMessage,
        blockSize,
        permutation,
        shift
    );

    Integer normalizedShift = shift % 256;

    String permutedMessage = "permutedMessage";
    String paddedMessage = "paddedMessage";
    String decryptedMessage = "decryptedMessage";

    List<AuditStep> auditSteps = List.of();

    when(shiftService.normalizeShift(shift)).thenReturn(
        normalizedShift
    );

    when(modularDecryptService.decrypt(encryptedMessage, normalizedShift)).thenReturn(
        permutedMessage
    );

    when(permutationDecryptService.decrypt(
        permutedMessage,
        request.permutation(),
        request.blockSize())
    ).thenReturn(
        paddedMessage
    );

    when(paddingDecryptService.decrypt(paddedMessage)).thenReturn(decryptedMessage);

    when(decryptAuditService.buildAuditSteps(
        encryptedMessage,
        permutedMessage,
        paddedMessage,
        decryptedMessage,
        request.blockSize(),
        request.permutation(),
        normalizedShift)
    ).thenReturn(auditSteps);

    decryptService.decrypt(request);

    InOrder inOrder = inOrder(
        validationService,
        shiftService,
        modularDecryptService,
        permutationDecryptService,
        paddingDecryptService,
        decryptAuditService
    );

    inOrder.verify(validationService).validateDecryptRequest(request);

    inOrder.verify(shiftService).normalizeShift(request.shift());
    inOrder.verify(modularDecryptService).decrypt(request.encryptedMessage(), normalizedShift);
    inOrder.verify(permutationDecryptService).decrypt(
        permutedMessage,
        request.permutation(),
        request.blockSize()
    );
    inOrder.verify(paddingDecryptService).decrypt(paddedMessage);
    inOrder.verify(decryptAuditService).buildAuditSteps(
        request.encryptedMessage(),
        permutedMessage,
        paddedMessage,
        decryptedMessage,
        request.blockSize(),
        request.permutation(),
        normalizedShift
    );
  }

}
