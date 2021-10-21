package com.ghertzsch.taxcalculator.plugins.database;

import com.ghertzsch.taxcalculator.domain.entities.TaxRate;
import com.ghertzsch.taxcalculator.domain.repositories.TaxRateRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InMemoryTaxRateRepository implements TaxRateRepository {

  private final ArrayList<TaxRate> taxRates = new ArrayList<>();

  @Override
  public void storeTaxRate(TaxRate taxRate) {
    taxRates.add(taxRate);
  }

  @Override
  public TaxRate findTaxRateById(UUID id) {
    return taxRates.stream()
      .filter(taxRate -> taxRate.getId().equals(id))
      .findFirst()
      .orElse(null);
  }

  @Override
  public List<TaxRate> findAllTaxRates() {
    return taxRates;
  }

}
