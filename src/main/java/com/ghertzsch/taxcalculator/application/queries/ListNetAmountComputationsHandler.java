package com.ghertzsch.taxcalculator.application.queries;

import com.ghertzsch.taxcalculator.domain.entities.NetAmountComputation;
import com.ghertzsch.taxcalculator.domain.queries.ListNetAmountComputations;
import com.ghertzsch.taxcalculator.domain.repositories.NetAmountComputationRepository;

import java.util.List;

public class ListNetAmountComputationsHandler {

  private NetAmountComputationRepository netAmountComputationRepository;

  public ListNetAmountComputationsHandler(NetAmountComputationRepository netAmountComputationRepository) {
    this.netAmountComputationRepository = netAmountComputationRepository;
  }

  public List<NetAmountComputation> handle(ListNetAmountComputations listNetAmountComputations) {
    return netAmountComputationRepository.findAllComputations();
  }
}
