package com.ghertzsch.taxcalculator.application.commands;

import com.ghertzsch.taxcalculator.application.events.NetAmountComputationPreparedHandler;
import com.ghertzsch.taxcalculator.application.events.NetAmountComputedHandler;
import com.ghertzsch.taxcalculator.domain.commands.PrepareNetAmountComputation;
import com.ghertzsch.taxcalculator.domain.events.NetAmountComputationPrepared;
import com.ghertzsch.taxcalculator.domain.repositories.NetAmountComputationRepository;

import java.util.UUID;

public class PrepareNetAmountComputationHandler {

  private NetAmountComputationRepository repository;

  public PrepareNetAmountComputationHandler(NetAmountComputationRepository repository) {
    this.repository = repository;
  }

  public void handle(PrepareNetAmountComputation prepareNetAmountComputation) {
    var event = new NetAmountComputationPrepared();
    var eventHandler = new NetAmountComputationPreparedHandler(
      repository
    );

    eventHandler.handle(event);
  }

}
