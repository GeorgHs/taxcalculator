package com.ghertzsch.taxcalculator.application.queries;

import com.ghertzsch.taxcalculator.domain.entities.TaxRate;
import com.ghertzsch.taxcalculator.domain.queries.ListTaxRates;
import com.ghertzsch.taxcalculator.domain.repositories.TaxRateRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ListTaxRatesHandler {

  private TaxRateRepository taxRateRepository;

  public ListTaxRatesHandler(TaxRateRepository taxRateRepository) {
    this.taxRateRepository = taxRateRepository;
  }

  public List<TaxRate> handle(ListTaxRates listTaxRates) {
    return taxRateRepository.findAllTaxRates().stream()
      .skip(listTaxRates.getSkip())
      .limit(listTaxRates.getTake())
      .collect(Collectors.toList());
  }
}
