package com.ghertzsch.taxcalculator.application.commands;

import com.ghertzsch.taxcalculator.application.events.NetAmountComputedHandler;
import com.ghertzsch.taxcalculator.domain.commands.ComputeNetAmount;
import com.ghertzsch.taxcalculator.domain.events.NetAmountComputed;
import com.ghertzsch.taxcalculator.domain.repositories.NetAmountComputationRepository;
import com.ghertzsch.taxcalculator.domain.repositories.TaxRateRepository;
import com.ghertzsch.taxcalculator.domain.valueobjects.GrossAmount;

public class ComputeNetAmountHandler {

  private final TaxRateRepository taxRateRepository;

  private final NetAmountComputationRepository netAmountComputationRepository;

  public ComputeNetAmountHandler(TaxRateRepository taxRateRepository, NetAmountComputationRepository netAmountComputationRepository) {
    this.taxRateRepository = taxRateRepository;
    this.netAmountComputationRepository = netAmountComputationRepository;
  }

  public void handle(ComputeNetAmount computeNetAmount) {
    var netAmountComputationId = computeNetAmount.getNetAmountComputationId();
    var taxRate = taxRateRepository.findTaxRateById(computeNetAmount.getTaxRateId());
    var grossAmount = new GrossAmount(computeNetAmount.getGrossAmount());

    var event = new NetAmountComputed(netAmountComputationId, taxRate, grossAmount);
    var eventHandler = new NetAmountComputedHandler(
      netAmountComputationRepository
    );
    eventHandler.handle(event);
  }

}
