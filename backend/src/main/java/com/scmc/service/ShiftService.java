package com.scmc.service;


import com.scmc.domain.dto.constants.CipherConstants;
import org.springframework.stereotype.Service;

@Service
public class ShiftService {

  public Integer normalizeShift(Integer shift) {
    return shift % CipherConstants.ASCII_RANGE;
  }
}
