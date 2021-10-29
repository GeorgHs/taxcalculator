package com.ghertzsch.taxcalculator.domain.entities;

import com.ghertzsch.taxcalculator.domain.valueobjects.Country;
import com.ghertzsch.taxcalculator.domain.valueobjects.TaxType;

import java.util.UUID;

public class TaxRate {

  private final UUID id = UUID.randomUUID();

  private final Country country;

  private final TaxType taxType;

  private final float value;

  public TaxRate(Country country, TaxType taxType, float value) {
    this.country = country;
    this.taxType = taxType;
    this.value = value;
  }

  public UUID getId() {
    return id;
  }

  public Country getCountry() {
    return country;
  }

  public TaxType getTaxType() {
    return taxType;
  }

  public float getValue() {
    return value;
  }

}
