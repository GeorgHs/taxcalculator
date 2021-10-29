package com.ghertzsch.taxcalculator.adapters;

import java.util.UUID;

public class ComputeNetAmountDTO {

  private String netAmountComputationId;

  private final String taxRateId;

  private final float grossAmount;

  public ComputeNetAmountDTO(String taxRateId, float grossAmount) {
    this.taxRateId = taxRateId;
    this.grossAmount = grossAmount;
  }

  public void setNetAmountComputationId(String netAmountComputationId) {
    this.netAmountComputationId = netAmountComputationId;
  }

  public UUID getNetAmountComputationId() {
    return UUID.fromString(netAmountComputationId);
  }

  public UUID getTaxRateId() {
    return UUID.fromString(taxRateId);
  }

  public float getGrossAmount() {
    return grossAmount;
  }
}
