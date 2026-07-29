package com.scmc.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.scmc.domain.dto.constants.CipherConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ValidationServiceTest {

  private ValidationService validationService;

  @BeforeEach
  public void setUp() {
    validationService = new ValidationService();
  }

  @Test
  public void shouldValidateMessageIsValid() {

    String validMessage = "Hello, World!";

    assertDoesNotThrow(() -> validationService.validateMessage(validMessage));
  }

  @Test
  public void shouldThrowExceptionWhenMessageIsNull() {

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> validationService.validateMessage(null)
    );

    assertEquals("El mensaje no puede estar vacío", exception.getMessage());
  }

  @Test
  public void shouldThrowExceptionWhenMessageIsEmpty() {

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> validationService.validateMessage("")
    );

    assertEquals("El mensaje no puede estar vacío", exception.getMessage());
  }

  @Test
  public void shouldValidatePaddingWhenMessageDoesNotContainIt() {

    String validMessage = "Hola, Mundo!";

    assertDoesNotThrow(() -> validationService.validatePaddingCharacter(validMessage));
  }

  @Test
  public void shouldThrowExceptionWhenMessageContainsPaddingCharacter() {

    String invalidMessage = "Hola" + CipherConstants.PADDING_CHARACTER + "Mundo!";

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> validationService.validatePaddingCharacter(invalidMessage)
    );

    assertEquals(
        "El mensaje no puede contener el carácter de relleno: "
            + CipherConstants.PADDING_CHARACTER,
        exception.getMessage()
    );
  }

  @Test
  public void shouldValidateBlockSizeWhenBlockSizeIsValid(){

    assertDoesNotThrow(() -> validationService.validateBlockSize(3, 10));
  }
}
