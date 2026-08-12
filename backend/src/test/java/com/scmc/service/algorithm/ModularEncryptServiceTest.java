package com.scmc.service.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ModularEncryptServiceTest {

  private ModularEncryptService modularEncryptService;

  @BeforeEach
  public void setUp() {
    modularEncryptService = new ModularEncryptService();
  }

  @Test
  public void shouldEncryptMessageWithPositiveShift() {
    String message = "Hello World!";
    Integer shift = 3;

    String encryptedMessage = modularEncryptService.encrypt(message, shift);

    assertEquals("Khoor#Zruog$", encryptedMessage);
  }

  @Test
  public void shouldReturnSameMessageWhenShiftIsZero() {
    String message = "Hello World!";
    Integer shift = 0;

    String encryptedMessage = modularEncryptService.encrypt(message, shift);

    assertEquals(message, encryptedMessage);
  }

  @Test
  public void shouldHandleWrapAroundWhenEncryptedAsciiExceedsRange() {
    String message = String.valueOf((char) 254);
    Integer shift = 5;

    String encryptedMessage = modularEncryptService.encrypt(message, shift);

    assertEquals((char) 3, encryptedMessage.charAt(0));
  }

  @Test
  public void shouldEncryptMultipleCharacters() {
    String message = String.valueOf(
        new char[]{
            (char) 105, (char) 102, (char) 109, (char) 109, (char) 112
        }
    );

    Integer shift = 1;

    String encryptedMessage = modularEncryptService.encrypt(message, shift);

    assertEquals(
        "jgnnq",
        encryptedMessage
    );

  }

  @Test
  public void shouldHandleShiftGreaterToAsciiRange(){
    String message = "Hola";
    Integer shift = 259;

    String encryptedMessage = modularEncryptService.encrypt(message, shift);

    String expectedEncryptedMessage = String.valueOf(
        new char[]{
            (char) (('H' + 259) % 256),
            (char) (('o' + 259) % 256),
            (char) (('l' + 259) % 256),
            (char) (('a' + 259) % 256)
        }
    );

    assertEquals(expectedEncryptedMessage, encryptedMessage);
  }

  @Test
  public void shouldEncryptEmptyMessage() {
    String message = "";
    Integer shift = 5;

    String encryptedMessage = modularEncryptService.encrypt(message, shift);

    assertEquals("", encryptedMessage);
  }

}
