package com.scmc.service.algorithm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModularDecryptServiceTest {

  private ModularDecryptService modularDecryptService;

  @BeforeEach
  public void setUp() {
    modularDecryptService = new ModularDecryptService();
  }

  @Test
  public void shouldDecryptMessageWithPositiveShift() {
    String cipherText = "Khoor#Zruog$";
    Integer shift = 3;

    String decryptedMessage = modularDecryptService.decrypt(cipherText, shift);

    assert decryptedMessage.equals("Hello World!");
  }
}
