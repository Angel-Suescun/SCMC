package com.scmc.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.scmc.domain.dto.DecryptRequest;
import com.scmc.domain.dto.EncryptRequest;
import com.scmc.domain.dto.constants.CipherConstants;
import java.util.ArrayList;
import java.util.List;
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
  public void shouldValidatePermutationWhenIsValid() {

    List <Integer> validPermutation = List.of(0, 1, 2, 3);

    assertDoesNotThrow(() -> validationService.validatePermutation(
        validPermutation,
        validPermutation.size())
    );
  }

  @Test
  public void shouldThrowExceptionWhenPermutationIsNull() {

    List <Integer> invalidPermutation = null;
    Integer blockSize = 4;

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> validationService.validatePermutation(
            invalidPermutation,
            blockSize
        )
    );

    assertEquals(
        "La permutación no puede estar vacía",
        exception.getMessage()
    );
  }

  @Test
  public void shouldThrowExceptionWhenPermutationIsEmpty() {

    List <Integer> invalidPermutation = new ArrayList<>();
    Integer blockSize = 4;

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> validationService.validatePermutation(
            invalidPermutation,
            blockSize
        )
    );

    assertEquals(
        "La permutación no puede estar vacía",
        exception.getMessage()
    );
  }

  @Test
  public void shouldThrowExceptionWhenPermutationSizeIsDifferentFromBlockSize() {

    List <Integer> invalidPermutation = List.of(0, 1, 2);
    Integer blockSize = invalidPermutation.size() + 1;

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> validationService.validatePermutation(
            invalidPermutation,
            blockSize
        )
    );

    assertEquals(
        "La permutación debe tener el mismo tamaño que el bloque",
        exception.getMessage()
    );
  }
  
  @Test
  public void shouldThrowExceptionWhenPermutationContainsNullValue() {

    List <Integer> invalidPermutation = new ArrayList<>();
    invalidPermutation.add(0);
    invalidPermutation.add(null);
    invalidPermutation.add(2);
    Integer blockSize = invalidPermutation.size();

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> validationService.validatePermutation(
            invalidPermutation,
            blockSize
        )
    );

    assertEquals(
        "La permutación no puede contener valores nulos",
        exception.getMessage()
    );
  }

  @Test
  public void shouldThrowExceptionWhenPermutationContainsNegativeValue() {

    List <Integer> invalidPermutation = List.of(0, -1, 2);
    Integer blockSize = invalidPermutation.size();

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> validationService.validatePermutation(
            invalidPermutation,
            blockSize
        )
    );

    assertEquals(
        "La permutación no puede contener valores negativos",
        exception.getMessage()
    );
  }

  @Test
  public void shouldThrowExceptionWhenPermutationContainsValueOutOfRangeIndex() {

    List <Integer> invalidPermutation = List.of(0, 1, 2, 5);
    Integer blockSize = 4;

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> validationService.validatePermutation(
            invalidPermutation,
            blockSize
        )
    );

    assertEquals(
        "La permutación contiene un índice fuera del rango del tamaño del bloque",
        exception.getMessage()
    );
  }

  @Test
  public void shouldThrowExceptionWhenPermutationContainsDuplicateIndices() {

    List <Integer> invalidPermutation = List.of(0, 1, 2, 2);
    Integer blockSize = invalidPermutation.size();

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> validationService.validatePermutation(
            invalidPermutation,
            blockSize
        )
    );

    assertEquals(
        "La permutación contiene índices duplicados",
        exception.getMessage()
    );
  }

  @Test
  public void shouldValidateMessageLengthIsMultipleOfBlockSize(){

    Integer messageLength = 12;
    Integer blockSize = 4;

    assertDoesNotThrow(() -> validationService.validateMessageLengthIsMultipleOfBlockSize(
        messageLength,
        blockSize
    ));
  }

  @Test
  public void shouldThrowExceptionWhenMessageLengthIsNotMultipleOfBlockSize(){

    Integer messageLength = 12;
    Integer blockSize = 15;

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> validationService.validateMessageLengthIsMultipleOfBlockSize(
            messageLength,
            blockSize
        )
    );

    assertEquals(
        "La longitud del mensaje cifrado debe ser multiplo del tamano del bloque",
        exception.getMessage()
    );
  }

  @Test
  public void shouldValidateEncryptRequestIsValid() {

    String message = "Hello, World!";
    Integer blockSize = 4;
    List<Integer> permutation = List.of(0, 1, 2, 3);
    Integer shift = 3;

    EncryptRequest validRequest = new EncryptRequest(
        message,
        blockSize,
        permutation,
        shift
    );

    assertDoesNotThrow(() -> validationService.validateEncryptRequest(validRequest));
  }

  @Test
  public void shouldValidateDecryptRequestIsValid() {

    String encryptedMessage = "Khoor, Zruog";
    Integer blockSize = 4;
    List<Integer> permutation = List.of(0, 1, 2, 3);
    Integer shift = 3;

    DecryptRequest validRequest = new DecryptRequest(
        encryptedMessage,
        blockSize,
        permutation,
        shift
    );

    assertDoesNotThrow(() -> validationService.validateDecryptRequest(validRequest));
  }
}
