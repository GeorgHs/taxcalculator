package com.ghertzsch.taxcalculator.adapters;

import com.ghertzsch.taxcalculator.domain.entities.TaxRate;

import java.util.function.Function;

public class TaxRateMapper implements Function<TaxRate, TaxRateDTO> {
  @Override
  public TaxRateDTO apply(TaxRate taxRate) {
    return new TaxRateDTO(
      taxRate.getId().toString(),
      taxRate.getCountry().name(),
      taxRate.getTaxType().name(),
      taxRate.getValue()
    );
  }
}
