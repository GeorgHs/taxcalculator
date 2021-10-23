package com.ghertzsch.taxcalculator.domain.factories;

import com.ghertzsch.taxcalculator.domain.entities.TaxRate;
import com.ghertzsch.taxcalculator.domain.valueobjects.Country;
import com.ghertzsch.taxcalculator.domain.valueobjects.TaxType;

public class TaxRateFactory implements Factory<TaxRate> {

  private Country country;

  private TaxType taxType;

  private float value;

  public TaxRateFactory() { }

  public TaxRateFactory WithCountry(Country country) {
    this.country = country;
    return this;
  }

  public TaxRateFactory OfType(TaxType taxType) {
    this.taxType = taxType;
    return this;
  }

  public TaxRateFactory WithValue(float value) {
    this.value = value;
    return this;
  }

  @Override
  public TaxRate build() {
    return new TaxRate(country, taxType, value);
  }
}
