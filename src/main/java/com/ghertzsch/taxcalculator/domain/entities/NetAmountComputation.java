package com.ghertzsch.taxcalculator.domain.entities;

import com.ghertzsch.taxcalculator.domain.valueobjects.NetAmount;

import java.util.UUID;

public class NetAmountComputation {

  private final UUID id = UUID.randomUUID();

  private final NetAmount netAmount;

  public NetAmountComputation(NetAmount netAmount) {
    this.netAmount = netAmount;
  }

  public UUID getId() {
    return id;
  }

  public NetAmount getNetAmount() {
    return netAmount;
  }

}
