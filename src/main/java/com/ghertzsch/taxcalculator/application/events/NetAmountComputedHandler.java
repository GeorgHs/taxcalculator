package com.ghertzsch.taxcalculator.application.events;

import com.ghertzsch.taxcalculator.domain.events.NetAmountComputed;
import com.ghertzsch.taxcalculator.domain.exceptions.DomainException;
import com.ghertzsch.taxcalculator.domain.repositories.NetAmountComputationRepository;
import com.ghertzsch.taxcalculator.domain.valueobjects.NetAmount;

public class NetAmountComputedHandler {

  private NetAmountComputationRepository netAmountComputationRepository;

  public NetAmountComputedHandler(NetAmountComputationRepository netAmountComputationRepository) {
    this.netAmountComputationRepository = netAmountComputationRepository;
  }

  public void handle(NetAmountComputed netAmountComputed) {
    var grossAmount = netAmountComputed.getGrossAmount();
    var taxRate = netAmountComputed.getTaxRate();

    var netAmount = new NetAmount(grossAmount.getValue() * (1 - taxRate.getValue()));

    var netAmountComputation = netAmountComputationRepository
      .findComputationById(netAmountComputed.getNetAmountComputationId());

    try {
      netAmountComputation.setGrossAmount(grossAmount);
      netAmountComputation.setTaxRate(taxRate);
      netAmountComputation.setNetAmount(netAmount);
    } catch (DomainException e) {
      // If there would be an event sourcing pattern in-place
      // then we would declare the event invalid here ...
      return;
    }

    netAmountComputationRepository.storeComputation(netAmountComputation);
  }

}
