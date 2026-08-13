package com.scmc.service.algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PermutationDecryptServiceTest {

  private PermutationDecryptService permutationDecryptService;

  @BeforeEach
  public void setUp() {
    permutationDecryptService = new PermutationDecryptService();
  }

  @Test
  public void shouldDecryptBlockUsingInversePermutation() {

    String block = "BADC";
    List<Integer> permutation = List.of(1, 0, 3, 2);
    Integer blockSize = 4;

    String decryptedBlock = permutationDecryptService.decrypt(block, permutation, blockSize);

    assertEquals("ABCD", decryptedBlock);
  }

  @Test
  public void shouldDecryptMessageWithMultipleBlocks(){

    String encryptedMessage = "BADCFEHG";
    List<Integer> permutation = List.of(1, 0, 3, 2);
    Integer blockSize = 4;

    String decryptedMessage = permutationDecryptService.decrypt(
        encryptedMessage,
        permutation,
        blockSize
    );

    assertEquals("ABCDEFGH", decryptedMessage);
  }

  @Test
  public void shouldReturnSameMessageWhenPermutationIsIdentity() {

    String encryptedMessage = "ABCDEFGH";
    List<Integer> permutation = List.of(0, 1, 2, 3);
    Integer blockSize = 4;

    String decryptedMessage = permutationDecryptService.decrypt(
        encryptedMessage,
        permutation,
        blockSize
    );

    assertEquals(encryptedMessage, decryptedMessage);
  }

  @Test
  public void shouldDecryptUsingNonSymmetricPermutation() {

    String encryptedMessage = "CABD";
    List<Integer> permutation = List.of(2, 0, 1, 3);
    Integer blockSize = 4;

    String decryptedMessage = permutationDecryptService.decrypt(
        encryptedMessage,
        permutation,
        blockSize
    );

    assertEquals("ABCD", decryptedMessage);

  }

  @Test
  public void shouldHandleSingleBlock(){

    String encryptedMessage = "DCBA";
    List<Integer> permutation = List.of(3, 2, 1, 0);
    Integer blockSize = 4;

    String decryptedMessage = permutationDecryptService.decrypt(
        encryptedMessage,
        permutation,
        blockSize
    );

    assertEquals("ABCD", decryptedMessage);
  }

  @Test
  public void shouldReturnEmptyStringWhenEncryptedTextIsEmpty(){

  String encryptedMessage = "";
  List<Integer> permutation = List.of(0, 1, 2, 3);
  Integer blockSize = 4;

  String decryptedMessage = permutationDecryptService.decrypt(
      encryptedMessage,
      permutation,
      blockSize
  );
  assertEquals(encryptedMessage, decryptedMessage);
  }

  @Test
  public void shouldThrowExceptionWhenEncryptedTextLengthIsNotMultipleOfBlockSize(){

    String encryptedMessage = "ABCDE";
    List<Integer> permutation = List.of(0, 1, 2, 3);
    Integer blockSize = 4;

    IllegalArgumentException exception = assertThrows(
        IllegalArgumentException.class,
        () -> permutationDecryptService.decrypt(
            encryptedMessage,
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


