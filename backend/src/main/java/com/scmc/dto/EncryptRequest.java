package com.scmc.dto;

import java.util.List;

public class EncryptRequest {
  private String message;

  private List<Integer> permutation;

  private Integer blockSize;

  private Integer shift;

  public EncryptRequest () {
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<Integer> getPermutation() {
    return permutation;
  }

  public void setPermutation(List<Integer> permutation) {
    this.permutation = permutation;
  }

  public Integer getShift() {
    return shift;
  }

  public void setShift(Integer shift) {
    this.shift = shift;
  }

  public Integer getBlockSize() {
    return blockSize;
  }

  public void setBlockSize(Integer blockSize) {
    this.blockSize = blockSize;
  }
}
