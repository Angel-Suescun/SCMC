package com.scmc.service.algorithm;

import com.scmc.domain.dto.constants.CipherConstants;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class PermutationEncryptService {

  public String encrypt(String paddedMessage, List<Integer> permutation, Integer blockSize) {

    List<String> blocks = splitIntoBlocks(paddedMessage, blockSize);

    StringBuilder encryptedMessage = new StringBuilder();

    for (String block : blocks) {
      encryptedMessage.append(applyPermutation(block,permutation));
    }

    return encryptedMessage.toString();

  }

  private List<String> splitIntoBlocks(String message, Integer blockSize) {

    List<String> blocks = new ArrayList<>();

    for (int i = 0; i < message.length(); i += blockSize) {
      blocks.add(message.substring(i, i + blockSize));
    }

    return blocks;
  }

  private String applyPermutation(String block, List<Integer> permutation) {

    StringBuilder result = new StringBuilder();

    for (Integer index : permutation) {
      result.append(block.charAt(index));
    }

    return result.toString();
  }
}
