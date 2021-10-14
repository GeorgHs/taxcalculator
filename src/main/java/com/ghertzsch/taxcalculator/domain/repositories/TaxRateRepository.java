package com.ghertzsch.taxcalculator.domain.repositories;

import com.ghertzsch.taxcalculator.domain.entities.TaxRate;

import java.util.List;
import java.util.UUID;

public interface TaxRateRepository {

  void storeTaxRate(TaxRate taxRate);

  TaxRate findTaxRateById(UUID id);

  List<TaxRate> findAllTaxRates();

}
