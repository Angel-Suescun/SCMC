package com.scmc.domain.builder;

import com.scmc.domain.dto.audit.AuditStep;
import com.scmc.domain.dto.audit.AuditType;

public class AuditStepBuilder {

  private Integer stepNumber;

  private AuditType title;

  private String description;

  private String input;

  private String output;

  public AuditStepBuilder setStepNumber(Integer stepNumber) {
    this.stepNumber = stepNumber;
    return this;
  }

  public AuditStepBuilder setTitle(AuditType title) {
    this.title = title;
    return this;
  }

  public AuditStepBuilder setDescription(String description) {
    this.description = description;
    return this;
  }

  public AuditStepBuilder setInput(String input) {
    this.input = input;
    return this;
  }

  public AuditStepBuilder setOutput(String output) {
    this.output = output;
    return this;
  }

  public AuditStep build() {
    return new AuditStep(stepNumber, title, description, input, output);
  }

}
