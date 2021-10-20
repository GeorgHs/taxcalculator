package com.ghertzsch.taxcalculator.domain.entities;

import com.ghertzsch.taxcalculator.domain.valueobjects.GrossAmount;
import com.ghertzsch.taxcalculator.domain.valueobjects.NetAmount;

import java.util.UUID;

public class NetAmountComputation {

  private final UUID id = UUID.randomUUID();

  private final NetAmount netAmount;

  private final GrossAmount grossAmount;

  private final TaxRate taxRate;

  public NetAmountComputation(NetAmount netAmount, GrossAmount grossAmount, TaxRate taxRate) {
    this.netAmount = netAmount;
    this.grossAmount = grossAmount;
    this.taxRate = taxRate;
  }

  public UUID getId() {
    return id;
  }

  public NetAmount getNetAmount() {
    return netAmount;
  }

  public GrossAmount getGrossAmount() {
    return grossAmount;
  }

  public TaxRate getTaxRate() {
    return taxRate;
  }
}
