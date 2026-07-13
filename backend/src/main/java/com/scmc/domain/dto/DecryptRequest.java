package com.scmc.domain.dto;

import java.util.List;

public record DecryptRequest (
    String  encryptedMessage,
    Integer blockSize,
    List<Integer> permutation,
    Integer shift
){
}
