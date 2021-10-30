package com.ghertzsch.taxcalculator.domain.services;

import com.ghertzsch.taxcalculator.domain.entities.NetAmountComputation;

import java.util.UUID;

public interface NetAmountComputationDomainService  {
  void initializeService(String netAmountComputationId);
  NetAmountComputation getCurrentNetAmountComputation();
}
