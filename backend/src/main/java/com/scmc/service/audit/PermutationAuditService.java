package com.scmc.service.audit;

import com.scmc.domain.builder.AuditStepBuilder;
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
    return new AuditStepBuilder()
        .setStepNumber(counterService.incrementStepCounter())
        .setTitle(AuditType.PERMUTACION)
        .setDescription(buildDescriptionForEncrypt(blockSize, permutation))
        .setInput(paddedMessage)
        .setOutput(permutedMessage)
        .build();
  }

  public AuditStep createDecryptAuditStep(
    AuditStepCounterService counterService,
    String permutedMessage,
    String paddedMessage,
    Integer blockSize,
    List<Integer> permutation
  ) {
    return new AuditStepBuilder()
        .setStepNumber(counterService.incrementStepCounter())
        .setTitle(AuditType.DESPERMUTACION)
        .setDescription(buildDescriptionForDecrypt(blockSize, permutation))
        .setInput(permutedMessage)
        .setOutput(paddedMessage)
        .build();
  }

  private String buildDescriptionForEncrypt(Integer blockSize, List<Integer> permutation) {
    return String.format(
        "Se ha aplicado una permutación de tamaño de bloque %d al mensaje, "
            + "utilizando la siguiente permutación: %s.",
        blockSize,
        permutation.toString()
    );
  }

  private String buildDescriptionForDecrypt(Integer blockSize, List<Integer> permutation) {
    return String.format(
        "Se dividió el mensaje en bloques de %d caracteres "
            + "y se aplicó la permutación inversa correspondiente %s a cada bloque.",
        blockSize,
        permutation.toString()
    );
  }

}
