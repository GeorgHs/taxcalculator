package com.ghertzsch.taxcalculator.application.commands;

import com.ghertzsch.taxcalculator.application.events.NetAmountComputedHandler;
import com.ghertzsch.taxcalculator.domain.commands.PrepareNetAmountComputation;
import com.ghertzsch.taxcalculator.domain.events.NetAmountComputed;
import com.ghertzsch.taxcalculator.domain.repositories.NetAmountComputationRepository;
import com.ghertzsch.taxcalculator.domain.repositories.TaxRateRepository;
import com.ghertzsch.taxcalculator.domain.valueobjects.GrossAmount;

public class PrepareNetAmountComputationHandler {

  private final TaxRateRepository taxRateRepository;

  private final NetAmountComputationRepository netAmountComputationRepository;

  public PrepareNetAmountComputationHandler(TaxRateRepository taxRateRepository, NetAmountComputationRepository netAmountComputationRepository) {
    this.taxRateRepository = taxRateRepository;
    this.netAmountComputationRepository = netAmountComputationRepository;
  }

  public void handle(PrepareNetAmountComputation prepareNetAmountComputation) {
    var taxRate = taxRateRepository.findTaxRateById(prepareNetAmountComputation.getTaxRateId());
    var grossAmount = new GrossAmount(prepareNetAmountComputation.getGrossAmount());

    var event = new NetAmountComputed(taxRate, grossAmount);
    var eventHandler = new NetAmountComputedHandler(
      netAmountComputationRepository
    );
    eventHandler.handle(event);
  }

}
