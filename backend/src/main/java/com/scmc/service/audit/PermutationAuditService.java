package com.scmc.service.audit;

import com.scmc.domain.dto.audit.AuditStep;
import com.scmc.domain.dto.audit.AuditType;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PermutationAuditService {

  public AuditStep createEncryptAuditStep(
    AuditStepCounterService counterService,
    String paddedMessage,
    String permutedMessage,
    Integer blockSize,
    List<Integer> permutation

  ) {
    return new AuditStep(
        counterService.incrementStepCounter(),
        AuditType.PERMUTACION,
        buildDescriptionForEncrypt(blockSize, permutation),
        paddedMessage,
        permutedMessage
    );
  }

  public AuditStep createDecryptAuditStep(
    AuditStepCounterService counterService,
    String permutedMessage,
    String paddedMessage,
    Integer blockSize,
    List<Integer> permutation
  ) {
    return new AuditStep(
        counterService.incrementStepCounter(),
        AuditType.DESPERMUTACION,
        buildDescriptionForDecrypt(blockSize, permutation),
        permutedMessage,
        paddedMessage
    );
  }

  private String buildDescriptionForEncrypt(Integer blockSize, List<Integer> permutation) {
    return String.format(
        "Se ha aplicado una permutación de tamaño de bloque %d al mensaje, "
            + "utilizando la siguiente permutación: %s.",
        blockSize,
        PermutationToString(permutation)
    );
  }

  private String buildDescriptionForDecrypt(Integer blockSize, List<Integer> permutation) {
    return String.format(
        "Se dividió el mensaje en bloques de %d caracteres "
            + "y se aplicó la permutación inversa correspondiente %s a cada bloque.",
        blockSize,
        PermutationToString(permutation)
    );
  }

  private String PermutationToString(List<Integer> permutation) {
    return permutation.stream()
        .map(i -> i + 1)
        .toList()
        .toString();
  }

}
