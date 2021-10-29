package com.ghertzsch.taxcalculator.domain.events;

import java.util.UUID;

public class Event {

  private final UUID id = UUID.randomUUID();

  public UUID getId() {
    return id;
  }

}
