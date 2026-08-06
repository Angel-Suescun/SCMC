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

    Integer blockSize = 4;
    Integer messageLength = 12;

    assertDoesNotThrow(() -> validationService.validateBlockSize(blockSize, messageLength));

  }

  @Test
  public void shouldThrowExceptionWhenBlockSizeIsNull() {

    Integer blockSize = null;
    Integer messageLength = 10;

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> validationService.validateBlockSize(blockSize, messageLength)
    );

    assertEquals("El tamaño del bloque no puede ser nulo", exception.getMessage());
  }

  @Test
  public void shouldThrowExceptionWhenBlockSizeIsLessThanTwo(){

    Integer blockSize = 1;
    Integer messageLength = 10;

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> validationService.validateBlockSize(blockSize, messageLength)
    );

    assertEquals("El tamaño del bloque debe ser mayor o igual a 2", exception.getMessage());
  }

  @Test
  public void shouldThrowExceptionWhenBlockSizeIsGreaterThanMessageLength(){

    Integer blockSize = 15;
    Integer messageLength = 10;

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> validationService.validateBlockSize(blockSize, messageLength)
    );

    assertEquals(
        "El tamaño del bloque no puede ser mayor que la longitud del mensaje",
        exception.getMessage()
    );
  }

  @Test
  public void shouldValidateBlockSizeWhenItIsEqualToMessageLength(){

    Integer blockSize = 10;
    Integer messageLength = 10;

    assertDoesNotThrow(() -> validationService.validateBlockSize(blockSize, messageLength));
  }
  

  @Test
  public void shouldThrowExceptionWhenMessageLengthIsNotMultipleOfBlockSize(){

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> validationService.validateMessageLengthIsMultipleOfBlockSize(
            12,
            15
        )
    );

    assertEquals(
        "La longitud del mensaje cifrado debe ser multiplo del tamano del bloque",
        exception.getMessage()
    );
  }
}
