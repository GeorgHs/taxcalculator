package com.ghertzsch.taxcalculator.application.queries;

import com.ghertzsch.taxcalculator.domain.entities.NetAmountComputation;
import com.ghertzsch.taxcalculator.domain.queries.GetNetAmountComputation;
import com.ghertzsch.taxcalculator.domain.repositories.NetAmountComputationRepository;

public class GetNetAmountComputationHandler {

  private NetAmountComputationRepository netAmountComputationRepository;

  public GetNetAmountComputationHandler(NetAmountComputationRepository netAmountComputationRepository) {
    this.netAmountComputationRepository = netAmountComputationRepository;
  }

  public NetAmountComputation handle(GetNetAmountComputation getNetAmountComputation) {
    return netAmountComputationRepository.findComputationById(getNetAmountComputation.getId());
  }
}
