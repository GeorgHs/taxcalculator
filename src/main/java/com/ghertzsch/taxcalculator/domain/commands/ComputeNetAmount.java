package com.ghertzsch.taxcalculator.domain.commands;

import java.util.UUID;

public class ComputeNetAmount {

  private final UUID netAmountComputationId;

  private final UUID taxRateId;

  private final float grossAmount;

  public ComputeNetAmount(UUID netAmountComputationId, UUID taxRateId, float grossAmount) {
    this.netAmountComputationId = netAmountComputationId;
    this.taxRateId = taxRateId;
    this.grossAmount = grossAmount;
  }

  public UUID getNetAmountComputationId() {
    return netAmountComputationId;
  }

  public UUID getTaxRateId() {
    return taxRateId;
  }

  public float getGrossAmount() {
    return grossAmount;
  }
}
