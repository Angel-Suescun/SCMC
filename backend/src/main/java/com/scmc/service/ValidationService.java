package com.scmc.service;

import com.scmc.domain.dto.EncryptRequest;
import com.scmc.domain.dto.constants.CipherConstants;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

  public void validateMessage(String message) {
    if (message == null || message.isEmpty()) {
      throw new IllegalArgumentException("El mensaje no puede estar vacío");
    }

    if (message.indexOf(CipherConstants.PADDING_CHARACTER) >= 0) {
      throw new IllegalArgumentException(
          "El mensaje no puede contener el carácter de relleno: " + CipherConstants.PADDING_CHARACTER
      );
    }
  }

  public void validateBlockSize(Integer blockSize, Integer messageLength) {
    if (blockSize == null){
      throw new IllegalArgumentException("El tamaño del bloque no puede ser nulo");
    }

    if (blockSize < 2){
      throw new IllegalArgumentException("El tamaño del bloque debe ser mayor o igual a 2");
    }

    if (blockSize > messageLength){
      throw new IllegalArgumentException(
          "El tamaño del bloque no puede ser mayor que la longitud del mensaje"
      );
    }
  }

  public void validatePermutation(List<Integer> permutation, Integer blockSize){
    if (permutation == null || permutation.isEmpty()) {
      throw new IllegalArgumentException("La permutación no puede estar vacía");
    }

    if (permutation.size() != blockSize) {
      throw new IllegalArgumentException(
          "La permutación debe tener el mismo tamaño que el bloque"
      );
    }

    Set<Integer> uniqueIndices = new HashSet<>();
    for (Integer value : permutation) {

      if (value == null){
        throw new IllegalArgumentException("La permutación no puede contener valores nulos");
      }

      if(value < 0){
        throw new IllegalArgumentException("La permutación no puede contener valores negativos");
      }

      if (value >= blockSize) {
        throw new IllegalArgumentException(
            "La permutación contiene un índice fuera del rango del tamaño del bloque"
        );
      }

      if(!uniqueIndices.add(value)){
        throw new IllegalArgumentException(
            "La permutación contiene índices duplicados"
        );
      }
    }
  }

  public void validateEncryptRequest(EncryptRequest request) {
    validateMessage(request.message());
    validateBlockSize(request.blockSize(), request.message().length());
    validatePermutation(request.permutation(), request.blockSize());
  }

//  public void validateDecryptRequest(DecryptRequest request) {
//    validateMessage(request.getMessage());
//    validateBlockSize(blockSize, encryptedMessage.length());
//    validatePermutation(permutation, blockSize);
//  }
}
