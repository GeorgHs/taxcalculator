package com.ghertzsch.taxcalculator.domain.entities;

import com.ghertzsch.taxcalculator.domain.exceptions.DomainException;
import com.ghertzsch.taxcalculator.domain.valueobjects.GrossAmount;
import com.ghertzsch.taxcalculator.domain.valueobjects.NetAmount;

import java.util.UUID;

public class NetAmountComputation {

  private final UUID id;

  private NetAmount netAmount = new NetAmount(0);

  private GrossAmount grossAmount = new GrossAmount(0);

  private TaxRate taxRate;

  public NetAmountComputation(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }

  public NetAmount getNetAmount() {
    return netAmount;
  }

  public void setNetAmount(NetAmount netAmount) throws DomainException {
    if (netAmount.getValue() < 0) {
      throw new DomainException("Value of netAmount can not be negative");
    }
    this.netAmount = netAmount;
  }

  public GrossAmount getGrossAmount() {
    return grossAmount;
  }

  public void setGrossAmount(GrossAmount grossAmount) throws DomainException {
    if (grossAmount.getValue() < 0) {
      throw new DomainException("Value of grossAmount can not be negative");
    }
    this.grossAmount = grossAmount;
  }

  public TaxRate getTaxRate() {
    return taxRate;
  }

  public void setTaxRate(TaxRate taxRate) {
    this.taxRate = taxRate;
  }
}
