package com.ghertzsch.taxcalculator.adapters;


/**
 * Unter Beachtung der Anti-Corruption-Layer wird hier das DTO zum Abfragen genutzt. NUR DATENSATZ
 * DTO = Resource (synonym)
 */
public class ListNetAmountComputationsDTO {
  private final int skip;
  private final int limit;

  public ListNetAmountComputationsDTO(int skip, int take) {
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
