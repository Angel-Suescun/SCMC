package com.scmc.domain.dto;

import java.util.List;

public record EncryptRequest (
  String message,
  Integer blockSize,
  List<Integer> permutation,
  Integer shift
) {

}

