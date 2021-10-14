package com.ghertzsch.taxcalculator.application;

import com.ghertzsch.taxcalculator.domain.entities.NetAmountComputation;
import com.ghertzsch.taxcalculator.domain.events.NetAmountComputed;
import com.ghertzsch.taxcalculator.domain.repositories.NetAmountComputationRepository;
import com.ghertzsch.taxcalculator.domain.valueobjects.NetAmount;

public class NetAmountComputedHandler {

  private NetAmountComputationRepository netAmountComputationRepository;

  public NetAmountComputedHandler(NetAmountComputationRepository netAmountComputationRepository) {
    this.netAmountComputationRepository = netAmountComputationRepository;
  }

  public void handle(NetAmountComputed netAmountComputed) {
    var grossAmountValue = netAmountComputed.getGrossAmount().getValue();
    var taxRateValue = netAmountComputed.getTaxRate().getValue();

    var netAmount = new NetAmount(grossAmountValue * (1 - taxRateValue));

    var netAmountComputation = new NetAmountComputation(netAmount);
    netAmountComputationRepository.storeComputation(netAmountComputation);
  }

}
