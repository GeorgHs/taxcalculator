package com.ghertzsch.taxcalculator.plugins.database;

import com.ghertzsch.taxcalculator.domain.entities.NetAmountComputation;
import com.ghertzsch.taxcalculator.domain.repositories.NetAmountComputationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InMemoryNetAmountComputationRepository implements NetAmountComputationRepository {

  private final ArrayList<NetAmountComputation> netAmountComputations = new ArrayList<>();

  @Override
  public void storeComputation(NetAmountComputation netAmountComputation) {
    netAmountComputations.add(netAmountComputation);
  }

  @Override
  public List<NetAmountComputation> findAllComputations() {
    return netAmountComputations;
  }

  @Override
  public NetAmountComputation findComputationById(UUID id) {
    return netAmountComputations.stream()
      .filter(netAmountComputation -> netAmountComputation.getId() == id)
      .findFirst()
      .orElse(null);
  }

}
