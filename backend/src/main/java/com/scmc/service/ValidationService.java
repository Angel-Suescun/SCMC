package com.scmc.service;

import com.scmc.domain.dto.constants.CipherConstants;
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
}
