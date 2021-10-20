package com.ghertzsch.taxcalculator.domain.events;

import com.ghertzsch.taxcalculator.domain.entities.TaxRate;
import com.ghertzsch.taxcalculator.domain.valueobjects.GrossAmount;

import java.util.UUID;

public class NetAmountComputed extends Event {

  private final TaxRate taxRate;

  private final GrossAmount grossAmount;

  public NetAmountComputed(TaxRate taxRate, GrossAmount grossAmount) {
    this.taxRate = taxRate;
    this.grossAmount = grossAmount;
  }

  public TaxRate getTaxRate() {
    return taxRate;
  }

  public GrossAmount getGrossAmount() {
    return grossAmount;
  }

}
