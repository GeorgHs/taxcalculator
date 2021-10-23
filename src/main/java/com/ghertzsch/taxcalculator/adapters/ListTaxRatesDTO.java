package com.ghertzsch.taxcalculator.adapters;

public class ListTaxRatesDTO {
  private final int skip;
  private final int limit;

  public ListTaxRatesDTO(int skip, int take) {
    this.skip = skip;
    this.limit = take;
  }

  public int getSkip() {
    return skip;
  }

  public int getLimit() {
    return limit;
  }
}
