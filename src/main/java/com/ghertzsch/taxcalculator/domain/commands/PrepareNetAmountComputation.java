package com.ghertzsch.taxcalculator.domain.commands;

import java.util.UUID;

public class PrepareNetAmountComputation {
  private final UUID taxRateId;

  private final float grossAmount;

  public PrepareNetAmountComputation(UUID taxRateId, float grossAmount) {
    this.taxRateId = taxRateId;
    this.grossAmount = grossAmount;
  }

  public UUID getTaxRateId() {
    return taxRateId;
  }

  public float getGrossAmount() {
    return grossAmount;
  }
}
