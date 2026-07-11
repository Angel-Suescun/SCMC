package com.scmc.service.audit;

import com.scmc.domain.builder.AuditStepBuilder;
import com.scmc.domain.dto.audit.AuditStep;
import com.scmc.domain.dto.audit.AuditType;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PermutationAuditService {

  public AuditStep createAuditStep(
    AuditStepCounterService counterService,
    String paddedMessage,
    String permutedMessage,
    Integer blockSize,
    List<Integer> permutation

  ) {
    return new AuditStepBuilder()
        .setStepNumber(counterService.incrementStepCounter())
        .setTitle(AuditType.PERMUTACION)
        .setDescription(buildDescription(blockSize, permutation))
        .setInput(paddedMessage)
        .setOutput(permutedMessage)
        .build();
  }

  private String buildDescription(Integer blockSize, List<Integer> permutation) {
    return String.format(
        "Se ha aplicado una permutación de tamaño de bloque %d al mensaje, "
            + "utilizando la siguiente permutación: %s.",
        blockSize,
        permutation.toString()
    );
  }

}
