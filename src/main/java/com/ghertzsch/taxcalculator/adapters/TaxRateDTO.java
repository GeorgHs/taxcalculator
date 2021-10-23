package com.ghertzsch.taxcalculator.adapters;

public class TaxRateDTO {
  private final String id;
  private final String country;
  private final String taxType;
  private final float value;

  public TaxRateDTO(String id, String country, String taxType, float value) {
    this.id = id;
    this.country = country;
    this.taxType = taxType;
    this.value = value;
  }

  public String getId() {
    return id;
  }

  public String getCountry() {
    return country;
  }

  public String getTaxType() {
    return taxType;
  }

  public float getValue() {
    return value;
  }
}
