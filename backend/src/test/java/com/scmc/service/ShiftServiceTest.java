package com.scmc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShiftServiceTest {

  private ShiftService shiftService;

  @BeforeEach
  public void setUp() {
    shiftService = new ShiftService();
  }

  @Test
  public void shouldReturnSameShiftWhenItIsWithinAsciiRange() {
    Integer shift = 100;

    assertEquals(shift, shiftService.normalizeShift(shift));
  }

  @Test
  public void shouldReturnZeroWhenShiftIsEqualToAsciiRange() {
    Integer shift = 256;
    Integer expectedNormalizedShift = 256 % 256;

    assertEquals(expectedNormalizedShift, shiftService.normalizeShift(shift));
  }

  @Test
  public void shouldNormalizedShiftWhenGreaterThanAsciiRange() {
    Integer shift = 300;
    Integer expectedNormalizedShift = 300 % 256;

    assertEquals(expectedNormalizedShift, shiftService.normalizeShift(shift));
  }

  @Test
  public void shouldReturnZeroWhenShiftIsZero() {
    Integer shift = 0;

    assertEquals(shift, shiftService.normalizeShift(shift));
  }

  @Test
  public void shouldReturnZeroWhenShiftIsNegative() {
    Integer shift = -100;

    assertEquals(shift, shiftService.normalizeShift(shift));
  }
}
