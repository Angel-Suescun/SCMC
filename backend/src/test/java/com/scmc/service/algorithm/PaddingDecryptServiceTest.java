package com.scmc.service.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.scmc.domain.dto.constants.CipherConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaddingDecryptServiceTest {

  private PaddingDecryptService paddingDecryptService;

  @BeforeEach
  public void setUp() {
    paddingDecryptService = new PaddingDecryptService();
  }

  @Test
  public void shouldRemoveSinglePaddingCharacterAtEnd() {

    String paddedMessage = "Hola" + CipherConstants.PADDING_CHARACTER;

    String expectedMessage = "Hola";

    String decryptedMessage = paddingDecryptService.decrypt(paddedMessage);

    assertEquals(expectedMessage, decryptedMessage);
  }

  @Test
  public void shouldRemoveMultiplePaddingCharactersAtEnd() {

    String paddedMessage = "Hola" +
        (CipherConstants.PADDING_CHARACTER) +
        (CipherConstants.PADDING_CHARACTER) +
        (CipherConstants.PADDING_CHARACTER);

    String expectedMessage = "Hola";

    String decryptedMessage = paddingDecryptService.decrypt(paddedMessage);

    assertEquals(expectedMessage, decryptedMessage);
  }

  @Test
  public void shouldReturnSameMessageWhenThereIsNoPaddingCharacter() {

    String message = "Hola";

    String decryptedMessage = paddingDecryptService.decrypt(message);

    assertEquals(message, decryptedMessage);
  }

  @Test
  public void shouldNotRemovePaddingCharacterInTheMiddleOfMessage(){

    String message = "Ho" + CipherConstants.PADDING_CHARACTER + "la";

    String decryptedMessage = paddingDecryptService.decrypt(message);

    assertEquals(message, decryptedMessage);
  }

  @Test
  public void shouldRemoveOnlyPaddingCharactersAtTheEnd(){

    String message = "Ho" + CipherConstants.PADDING_CHARACTER + "la" +
        (CipherConstants.PADDING_CHARACTER) +
        (CipherConstants.PADDING_CHARACTER);

    String expectedMessage = "Ho" + CipherConstants.PADDING_CHARACTER + "la";

    String decryptedMessage = paddingDecryptService.decrypt(message);

    assertEquals(expectedMessage, decryptedMessage);
  }

  @Test
  public void shouldReturnEmptyStringWhenMessageIsOnlyPaddingCharacters(){

    String message = "" +
        (CipherConstants.PADDING_CHARACTER) +
        (CipherConstants.PADDING_CHARACTER) +
        (CipherConstants.PADDING_CHARACTER);

    String expectedMessage = "";

    String decryptedMessage = paddingDecryptService.decrypt(message);

    assertEquals(expectedMessage, decryptedMessage);
  }

  @Test
  public void shouldReturnEmptyStringWhenMessageIsEmpty(){

    String message = "";

    String decryptedMessage = paddingDecryptService.decrypt(message);

    assertEquals(message, decryptedMessage);
  }
}
