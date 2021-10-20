package com.ghertzsch.taxcalculator.application.queries;

import com.ghertzsch.taxcalculator.domain.entities.NetAmountComputation;
import com.ghertzsch.taxcalculator.domain.queries.ListNetAmountComputations;
import com.ghertzsch.taxcalculator.domain.repositories.NetAmountComputationRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ListNetAmountComputationsHandler {

  private NetAmountComputationRepository netAmountComputationRepository;

  public ListNetAmountComputationsHandler(NetAmountComputationRepository netAmountComputationRepository) {
    this.netAmountComputationRepository = netAmountComputationRepository;
  }

  public List<NetAmountComputation> handle(ListNetAmountComputations listNetAmountComputations) {
    return netAmountComputationRepository.findAllComputations().stream()
      .skip(listNetAmountComputations.getSkip())
      .limit(listNetAmountComputations.getLimit())
      .collect(Collectors.toList());
  }
}
