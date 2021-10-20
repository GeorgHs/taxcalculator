package com.ghertzsch.taxcalculator.domain.queries;

import java.util.UUID;

public class GetNetAmountComputation {
  private final UUID id;

  public GetNetAmountComputation(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }
}
