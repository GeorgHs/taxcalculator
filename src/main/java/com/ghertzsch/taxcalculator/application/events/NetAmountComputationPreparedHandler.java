package com.ghertzsch.taxcalculator.application.events;

import com.ghertzsch.taxcalculator.domain.entities.NetAmountComputation;
import com.ghertzsch.taxcalculator.domain.events.NetAmountComputationPrepared;
import com.ghertzsch.taxcalculator.domain.repositories.NetAmountComputationRepository;

public class NetAmountComputationPreparedHandler {

  private final NetAmountComputationRepository repository;

  public NetAmountComputationPreparedHandler(NetAmountComputationRepository repository) {
    this.repository = repository;
  }

  public void handle(NetAmountComputationPrepared netAmountComputationPrepared) {
    repository.storeComputation(new NetAmountComputation(netAmountComputationPrepared.getNetAmountComputationId()));
  }

}
