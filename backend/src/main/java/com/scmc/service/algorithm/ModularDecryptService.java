package com.scmc.service.algorithm;


import com.scmc.domain.dto.constants.CipherConstants;
import org.springframework.stereotype.Service;

@Service
public class ModularDecryptService {

  public String decrypt(String cipherText, Integer shift) {

    StringBuilder decryptedMessage = new StringBuilder();

    for (char character : cipherText.toCharArray()) {

      int decryptedAscii =
          (character - shift + CipherConstants.ASCII_RANGE) % CipherConstants.ASCII_RANGE;

      decryptedMessage.append((char) decryptedAscii);
    }

    return decryptedMessage.toString();
  }
}
