package com.ghertzsch.taxcalculator.domain.queries;

public class ListTaxRates {

  private final int skip;
  private final int take;

  public ListTaxRates(int skip, int take) {
    this.skip = skip;
    this.take = take;
  }

  public int getSkip() {
    return skip;
  }

  public int getTake() {
    return take;
  }
}
