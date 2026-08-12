package com.scmc.service.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    assertEquals("Hello World!", decryptedMessage);
  }

  @Test
  public void shouldReturnSameMessageWhenShiftIsZero() {
    String cipherText = "Hello World!";
    Integer shift = 0;

    String decryptedMessage = modularDecryptService.decrypt(cipherText, shift);

    assertEquals(cipherText, decryptedMessage);
  }

  @Test
  public void shouldHandleWrapAroundWhenDecryptedAsciiBecomesNegative() {
    String cipherText = String.valueOf((char) 2);
    Integer shift = 5;

    String decryptedMessage = modularDecryptService.decrypt(cipherText, shift);

    assertEquals((char) 253, decryptedMessage.charAt(0));
  }

  @Test
  public void shouldDecryptMultipleCharacters() {
    String cipherText = String.valueOf(
        new char[]{
            (char) 105, (char) 102, (char) 109, (char) 109, (char) 112
        }
    );

    Integer shift = 1;

    String decryptedMessage = modularDecryptService.decrypt(cipherText, shift);

    assertEquals(
        "hello",
        decryptedMessage
    );
  }

  @Test
  public void shouldHandleShiftEqualToAsciiRange() {
    String cipherText = "Hello World!";
    Integer shift = 256;

    String decryptedMessage = modularDecryptService.decrypt(cipherText, shift);

    assertEquals(cipherText, decryptedMessage);
  }

  @Test
  public void shouldHandleShiftGreaterThanAsciiRange() {
    String cipherText = "Hola";
    Integer shift = 259;

    String decryptedMessage = modularDecryptService.decrypt(cipherText, shift);

    String expectedDecryptedMessage = String.valueOf(
        new char[]{
            (char) ((('H' - 259 + 256) % 256)),
            (char) ((('o' - 259 + 256) % 256)),
            (char) ((('l' - 259 + 256) % 256)),
            (char) ((('a' - 259 + 256) % 256))
        }
    );

    assertEquals(expectedDecryptedMessage, decryptedMessage);
  }

  @Test
  public void shouldHandleNegativeShift() {
    String cipherText = "Khoor#Zruog$";
    Integer shift = -3;

    String decryptedMessage = modularDecryptService.decrypt(cipherText, shift);

    String expectedDecryptedMessage = String.valueOf(
        new char[]{
            (char) ((('K' - (-3) + 256) % 256)),
            (char) ((('h' - (-3) + 256) % 256)),
            (char) ((('o' - (-3) + 256) % 256)),
            (char) ((('o' - (-3) + 256) % 256)),
            (char) ((('r' - (-3) + 256) % 256)),
            (char) ((('#' - (-3) + 256) % 256)),
            (char) ((('Z' - (-3) + 256) % 256)),
            (char) ((('r' - (-3) + 256) % 256)),
            (char) ((('u' - (-3) + 256) % 256)),
            (char) ((('o' - (-3) + 256) % 256)),
            (char) ((('g' - (-3) + 256) % 256)),
            (char) ((('$' - (-3) + 256) % 256))
        }
    );

    assertEquals(expectedDecryptedMessage, decryptedMessage);
  }

  @Test
  public void shouldDecryptEmptyMessage() {

    String cipherText = "";
    Integer shift = 5;

    String decryptedMessage = modularDecryptService.decrypt(cipherText, shift);

    assertEquals("", decryptedMessage); }
}
