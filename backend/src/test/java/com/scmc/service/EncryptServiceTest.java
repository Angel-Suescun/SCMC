package com.scmc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.scmc.domain.dto.EncryptRequest;
import com.scmc.domain.dto.EncryptResponse;
import com.scmc.domain.dto.audit.AuditStep;
import com.scmc.service.algorithm.ModularEncryptService;
import com.scmc.service.algorithm.PaddingEncryptService;
import com.scmc.service.algorithm.PermutationEncryptService;
import com.scmc.service.audit.EncryptAuditService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EncryptServiceTest {

  @Mock
  private ValidationService validationService;

  @Mock
  private ShiftService shiftService;

  @Mock
  private PaddingEncryptService paddingEncryptService;

  @Mock
  private PermutationEncryptService permutationEncryptService;

  @Mock
  private ModularEncryptService modularEncryptService;

  @Mock
  private EncryptAuditService encryptAuditService;

  @InjectMocks
  private EncryptService encryptService;

  @Test
  public void shouldEncryptMessageSuccessfully() {

    String message = "Hello, World!";
    int blockSize = 3;
    List<Integer> permutation = List.of(2, 0, 1);
    int shift = 261;


    EncryptRequest request = new EncryptRequest(
        message,
        blockSize,
        permutation,
        shift
    );

    Integer normalizedShift = 261 % 256;

    String paddedMessage = "paddedMessage";
    String permutedMessage = "permutedMessage";
    String encryptedMessage = "encryptedMessage";

    List<AuditStep> auditSteps = List.of();

    when(shiftService.normalizeShift(shift)).thenReturn(normalizedShift);

    when(paddingEncryptService.encrypt(
        request.message(),
        request.blockSize())
    ).thenReturn(
        paddedMessage
    );

    when(permutationEncryptService.encrypt(
        paddedMessage,
        request.permutation(),
        request.blockSize())
    ).thenReturn(
        permutedMessage
    );

    when(modularEncryptService.encrypt(
        permutedMessage,
        normalizedShift
        )
    ).thenReturn(
        encryptedMessage
    );

    when(encryptAuditService.buildAuditSteps(
        message,
        paddedMessage,
        permutedMessage,
        encryptedMessage,
        request.blockSize(),
        request.permutation(),
        normalizedShift
        )
    ).thenReturn(auditSteps);

    EncryptResponse response = encryptService.encrypt(request);

    assertEquals(message, response.getOriginalMessage());
    assertEquals(paddedMessage, response.getPaddedMessage());
    assertEquals(permutedMessage, response.getPermutedMessage());
    assertEquals(encryptedMessage, response.getEncryptedMessage());
    assertEquals(request.blockSize(), response.getBlockSize());
    assertEquals(request.permutation(), response.getPermutation());
    assertEquals(normalizedShift, response.getShift());
    assertEquals(auditSteps, response.getAudit());

    verify(validationService).validateEncryptRequest(request);
  }

  @Test
  public void shouldExecuteEncryptStepsInCorrectOrder() {
    String message = "Hello, World!";
    int blockSize = 3;
    List<Integer> permutation = List.of(2, 0, 1);
    int shift = 261;

    EncryptRequest request = new EncryptRequest(
        message,
        blockSize,
        permutation,
        shift
    );

    Integer normalizedShift = 261 % 256;

    String paddedMessage = "paddedMessage";
    String permutedMessage = "permutedMessage";
    String encryptedMessage = "encryptedMessage";

    List<AuditStep> auditSteps = List.of();

    when(shiftService.normalizeShift(shift)).thenReturn(normalizedShift);

    when(paddingEncryptService.encrypt(
        request.message(),
        request.blockSize())
    ).thenReturn(
        paddedMessage
    );

    when(permutationEncryptService.encrypt(
        paddedMessage,
        request.permutation(),
        request.blockSize())
    ).thenReturn(
        permutedMessage
    );

    when(modularEncryptService.encrypt(
            permutedMessage,
            normalizedShift
        )
    ).thenReturn(
        encryptedMessage
    );

    when(encryptAuditService.buildAuditSteps(
            message,
            paddedMessage,
            permutedMessage,
            encryptedMessage,
            request.blockSize(),
            request.permutation(),
            normalizedShift
        )
    ).thenReturn(auditSteps);

    encryptService.encrypt(request);

    InOrder inOrder = inOrder(
        validationService,
        shiftService,
        paddingEncryptService,
        permutationEncryptService,
        modularEncryptService,
        encryptAuditService
    );

    inOrder.verify(validationService).validateEncryptRequest(request);

    inOrder.verify(shiftService).normalizeShift(request.shift());

    inOrder.verify(paddingEncryptService).encrypt(request.message(), request.blockSize());

    inOrder.verify(permutationEncryptService).encrypt(
        paddedMessage,
        request.permutation(),
        request.blockSize()
    );

    inOrder.verify(modularEncryptService).encrypt(permutedMessage, normalizedShift);

    inOrder.verify(encryptAuditService).buildAuditSteps(
        request.message(),
        paddedMessage,
        permutedMessage,
        encryptedMessage,
        request.blockSize(),
        request.permutation(),
        normalizedShift
    );
  }
}
