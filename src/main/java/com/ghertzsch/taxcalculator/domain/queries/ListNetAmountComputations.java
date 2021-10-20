package com.ghertzsch.taxcalculator.domain.queries;

public class ListNetAmountComputations {
  private final int skip;
  private final int limit;

  public ListNetAmountComputations(int skip, int take) {
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
