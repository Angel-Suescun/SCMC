package com.scmc.service.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PermutationEncryptServiceTest {

  private PermutationEncryptService permutationEncryptService;

  @BeforeEach
  public void setUp() {
    permutationEncryptService = new PermutationEncryptService();
  }

  @Test
  public void shouldEncryptBlockUsingPermutation() {

    String block = "ABCD";
    List<Integer> permutation = List.of(1, 0, 3, 2);
    Integer blockSize = 4;

    String encryptedBlock = permutationEncryptService.encrypt(block, permutation, blockSize);

    assertEquals("BADC", encryptedBlock);
  }

  @Test
  public void shouldEncryptMessageWithMultipleBlocks(){

    String message = "ABCDEFGH";
    List<Integer> permutation = List.of(1, 0, 3, 2);
    Integer blockSize = 4;

    String encryptedMessage = permutationEncryptService.encrypt(
        message,
        permutation,
        blockSize
    );

    assertEquals("BADCFEHG", encryptedMessage);
  }

  @Test
  public void shouldReturnSameMessageWhenPermutationIsIdentity() {

    String message = "ABCDEFGH";
    List<Integer> permutation = List.of(0, 1, 2, 3);
    Integer blockSize = 4;

    String encryptedMessage = permutationEncryptService.encrypt(
        message,
        permutation,
        blockSize
    );

    assertEquals(message, encryptedMessage);
  }

  @Test
  public void shouldEncryptUsingNonSymmetricPermutation() {

    String message = "ABCD";
    List<Integer> permutation = List.of(2, 0, 3, 1);
    Integer blockSize = 4;

    String encryptedMessage = permutationEncryptService.encrypt(
        message,
        permutation,
        blockSize
    );

    assertEquals("CADB", encryptedMessage);
  }

  @Test
  public void shouldEncryptSingleBlock() {

    String message = "ABCD";
    List<Integer> permutation = List.of(3, 2, 1, 0);
    Integer blockSize = 4;

    String encryptedMessage = permutationEncryptService.encrypt(
        message,
        permutation,
        blockSize
    );

    assertEquals("DCBA", encryptedMessage);
  }

  @Test
  public void shouldReturnEmptyMessageWhenPaddedMessageIsEmpty() {

    String message = "";
    List<Integer> permutation = List.of(0, 1, 2, 3);
    Integer blockSize = 4;

    String encryptedMessage = permutationEncryptService.encrypt(
        message,
        permutation,
        blockSize
    );

    assertEquals("", encryptedMessage);
  }

  @Test
  public void shouldThrowExceptionWhenMessageLengthIsNotMultipleOfBlockSize() {

    String message = "ABCDE";
    List<Integer> permutation = List.of(0, 1, 2, 3);
    Integer blockSize = 4;

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> permutationEncryptService.encrypt(
            message,
            permutation,
            blockSize
      )
    );

    assertEquals(
        "La longitud del mensaje debe ser multiplo del tamano del bloque",
        exception.getMessage()
    );
  }

}
