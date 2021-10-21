package com.ghertzsch.taxcalculator.adapters;

public class PrepareNetAmountComputationDTO {
  private final String taxRateId;

  private final float grossAmount;

  public PrepareNetAmountComputationDTO(String taxRateId, float grossAmount) {
    this.taxRateId = taxRateId;
    this.grossAmount = grossAmount;
  }

  public String getTaxRateId() {
    return taxRateId;
  }

  public float getGrossAmount() {
    return grossAmount;
  }
}
