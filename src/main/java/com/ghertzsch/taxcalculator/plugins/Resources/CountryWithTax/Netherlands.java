package com.ghertzsch.taxcalculator.plugins.Resources.CountryWithTax;

import com.ghertzsch.taxcalculator.domain.factories.TaxRateFactory;
import com.ghertzsch.taxcalculator.domain.repositories.TaxRateRepository;
import com.ghertzsch.taxcalculator.domain.valueobjects.Country;
import com.ghertzsch.taxcalculator.domain.valueobjects.TaxType;

public class Netherlands {
  public static void generate(TaxRateRepository taxRateRepository) {
    var vat = new TaxRateFactory()
      .OfType(TaxType.VALUE_ADDED_TAX)
      .WithCountry(Country.NETHERLANDS)
      .WithValue(0.21f)
      .build();

    var capGains = new TaxRateFactory()
      .OfType(TaxType.CAPITAL_GAINS_TAX)
      .WithCountry(Country.NETHERLANDS)
      .WithValue(0.31f)
      .build();

    taxRateRepository.storeTaxRate(vat);
    taxRateRepository.storeTaxRate(capGains);
  };
}
