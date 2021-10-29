package com.ghertzsch.taxcalculator.plugins.repositories;

import com.ghertzsch.taxcalculator.domain.entities.NetAmountComputation;
import com.ghertzsch.taxcalculator.domain.repositories.NetAmountComputationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InMemoryNetAmountComputation implements NetAmountComputationRepository {

  private final ArrayList<NetAmountComputation> netAmountComputations = new ArrayList<>();

  @Override
  public void storeComputation(NetAmountComputation netAmountComputation) {
    var netAmountComputationIndex = netAmountComputations.indexOf(netAmountComputation);
    if (netAmountComputationIndex != -1) {
      netAmountComputations.set(netAmountComputationIndex, netAmountComputation);
      return;
    }
    netAmountComputations.add(netAmountComputation);
  }

  @Override
  public List<NetAmountComputation> findAllComputations() {
    return netAmountComputations;
  }

  @Override
  public NetAmountComputation findComputationById(UUID id) {
    return netAmountComputations.stream()
      .filter(netAmountComputation -> netAmountComputation.getId().equals(id))
      .findFirst()
      .orElse(null);
  }

}
