package com.scmc.domain.dto.audit;

public class AuditStep {

  private Integer stepNumber;

  private AuditStep title;

  private String description;

  private String input;

  private String output;

  public AuditStep() {
  }

  public AuditStep(
    Integer stepNumber,
    AuditStep title,
    String description,
    String input,
    String output
  ) {
    this.stepNumber = stepNumber;
    this.title = title;
    this.description = description;
    this.input = input;
    this.output = output;
  }

  public Integer getStepNumber() {
    return this.stepNumber;
  }

  public void setStepNumber(Integer stepNumber) {
    this.stepNumber = stepNumber;
  }

  public AuditStep getTitle() {
    return this.title;
  }

  public void setTitle(AuditStep title) {
    this.title = title;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getInput(){
    return this.input;
  }

  public void setInput(String input) {
    this.input = input;
  }

  public String getOutput() {
    return output;
  }

  public void setOutput(String output) {
    this.output = output;
  }
}
