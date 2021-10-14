package com.ghertzsch.taxcalculator.domain.repositories;

import com.ghertzsch.taxcalculator.domain.entities.NetAmountComputation;

import java.util.List;
import java.util.UUID;

public interface NetAmountComputationRepository {

  void storeComputation(NetAmountComputation netAmountComputation);

  List<NetAmountComputation> findAllComputations();

  NetAmountComputation findComputationById(UUID id);

}
