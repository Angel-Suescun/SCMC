package com.scmc.service.audit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuditStepCounterServiceTest {

  private AuditStepCounterService auditStepCounterService;

  @BeforeEach
  public void setUp() {
    auditStepCounterService = new AuditStepCounterService();
  }

  @Test
  public void shouldStartCounterAtOne() {
    assertEquals(1, auditStepCounterService.getStepCounter());
  }

  @Test
  public void shouldReturnCurrentStepAndIncrementCounter() {

    assertEquals(1, auditStepCounterService.incrementStepCounter());
    assertEquals(2, auditStepCounterService.getStepCounter());

    assertEquals(2, auditStepCounterService.incrementStepCounter());
    assertEquals(3, auditStepCounterService.getStepCounter());

    assertEquals(3, auditStepCounterService.incrementStepCounter());
    assertEquals(4, auditStepCounterService.getStepCounter());
  }

}
