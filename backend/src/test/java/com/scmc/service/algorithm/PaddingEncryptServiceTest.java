package com.scmc.service.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.scmc.domain.dto.constants.CipherConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaddingEncryptServiceTest {

  private PaddingEncryptService paddingEncryptService;

  @BeforeEach
  public void setUp() {
    paddingEncryptService = new PaddingEncryptService();
  }

  @Test
  public void shouldNotAddPaddingWhenMessageLengthIsMultipleOfBlockSize() {

    String message = "Hola";
    Integer blockSize = 4;

    String encryptedMessage = paddingEncryptService.encrypt(message, blockSize);

    assertEquals(message, encryptedMessage);
  }

  @Test
  public void shouldAddOnePaddingCharacterWhenOneCharacterIsMissing(){

    String message = "Hola";
    Integer blockSize = 5;

    String expectedMessage = "Hola" + CipherConstants.PADDING_CHARACTER;

    String encryptedMessage = paddingEncryptService.encrypt(message, blockSize);

    assertEquals(expectedMessage, encryptedMessage);
  }

  @Test
  public void shouldAddSeveralPaddingCharactersWhenNecessary(){

    String message = "Hola";
    Integer blockSize = 7;

    String expectedMessage = "Hola" +
        (CipherConstants.PADDING_CHARACTER) +
        (CipherConstants.PADDING_CHARACTER) +
        (CipherConstants.PADDING_CHARACTER);

    String encryptedMessage = paddingEncryptService.encrypt(message, blockSize);

    assertEquals(expectedMessage, encryptedMessage);

  }

  @Test
  public void shouldPadMessageUntilReachesBlockSize(){

    String message = "Hola";
    Integer blockSize = 5;

    String expectedMessage = "Hola" + CipherConstants.PADDING_CHARACTER;

    String encryptedMessage = paddingEncryptService.encrypt(message, blockSize);

    assertEquals(expectedMessage, encryptedMessage);
  }

  @Test
  public void shouldWorkWithBlockSizeOfTwo(){

    String message = "Hola";
    Integer blockSize = 2;

    String encryptedMessage = paddingEncryptService.encrypt(message, blockSize);

    assertEquals(message, encryptedMessage);
  }

  @Test
  public void shouldPadEmptyMessage(){

    String message = "";
    Integer blockSize = 3;

    String encryptedMessage = paddingEncryptService.encrypt(message, blockSize);

    assertEquals(message, encryptedMessage);
  }

  @Test
  public void shouldPadMessageShorterThanBlockSize() {

    String message = "Hi";
    Integer blockSize = 5;

    String expectedMessage = "Hi" +
        (CipherConstants.PADDING_CHARACTER) +
        (CipherConstants.PADDING_CHARACTER) +
        (CipherConstants.PADDING_CHARACTER);

    String result = paddingEncryptService.encrypt(message, blockSize);

    assertEquals(expectedMessage, result);
  }
}
