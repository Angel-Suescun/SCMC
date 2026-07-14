package com.scmc.service.algorithm;


import com.scmc.domain.dto.constants.CipherConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;

@Service
public class PermutationDecryptService {

  public String decrypt(
    String encryptedText,
    List<Integer> permutation,
    Integer blockSize
  ) {

    List<String> blocks = splitIntoBlocks(encryptedText, blockSize);

    List<Integer> inversePermutation = buildInversePermutation(permutation);

    StringBuilder paddedMessage = new StringBuilder();

    for (String block : blocks) {
      paddedMessage.append(applyInversePermutation(block, inversePermutation));
    }

    return paddedMessage.toString();
  }

  private List<String> splitIntoBlocks(String message,Integer blockSize) {

    List<String> blocks = new ArrayList<>();

    if (message.length() % blockSize != 0) {
      throw new IllegalArgumentException(
          "La longitud del mensaje debe ser multiplo del tamano del bloque"
      );
    }

    for (int i = 0; i < message.length(); i += blockSize) {
      blocks.add(message.substring(i, i + blockSize));
    }

    return blocks;
  }

  private List<Integer> buildInversePermutation(List<Integer> permutation) {

    List<Integer> inversePermutation = new ArrayList<>(
        Collections.nCopies(permutation.size(), 0)
    );

    for (int i = 0; i < permutation.size(); i++) {
      inversePermutation.set(permutation.get(i), i);
    }

    return inversePermutation;
  }

  private String applyInversePermutation(String block, List<Integer> inversePermutation) {

    StringBuilder result = new StringBuilder();

    for (Integer index : inversePermutation) {
      result.append(block.charAt(index));
    }

    return result.toString();
  }

}
