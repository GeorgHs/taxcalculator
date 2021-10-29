package com.ghertzsch.taxcalculator.domain.events;

import java.util.UUID;

public class NetAmountComputationPrepared extends Event {

  private final UUID netAmountComputationId = UUID.randomUUID();

  public UUID getNetAmountComputationId() {
    return netAmountComputationId;
  }

}
