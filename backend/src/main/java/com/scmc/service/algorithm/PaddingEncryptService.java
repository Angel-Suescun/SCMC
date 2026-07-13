package com.scmc.service.algorithm;

import org.springframework.stereotype.Service;

@Service
public class PaddingEncryptService {

  public String encrypt(String message, Integer blockSize) {

    StringBuilder paddedMessage = new StringBuilder(message);

    while (paddedMessage.length() % blockSize != 0) {
      paddedMessage.append(com.scmc.domain.dto.constants.CipherConstants.PADDING_CHARACTER);
    }

    return paddedMessage.toString();
  }
}
