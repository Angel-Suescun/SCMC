package com.scmc.service.audit;

public class AuditStepCounterService {

  private Integer stepCounter;

  public AuditStepCounterService() {
    this.stepCounter = 1;
  }

  public Integer getStepCounter() {
    return this.stepCounter;
  }

  public Integer incrementStepCounter() {

    return this.stepCounter++;
  }

}
