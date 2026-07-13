package com.scmc.service.algorithm;

import com.scmc.domain.dto.constants.CipherConstants;
import org.springframework.stereotype.Service;

@Service
public class ModularEncryptService {

  public String encrypt(String data,  int shift) {

    StringBuilder encryptedMessage = new StringBuilder();

    for (char character : data.toCharArray()) {

      int encryptedAscii = (character + shift) % CipherConstants.ASCII_RANGE;

      encryptedMessage.append((char) encryptedAscii);
    }
    
    return encryptedMessage.toString();

  }
}
