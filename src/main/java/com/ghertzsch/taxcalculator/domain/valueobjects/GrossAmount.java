package com.ghertzsch.taxcalculator.domain.valueobjects;

public class GrossAmount {

  private final float value;

  public GrossAmount(float value) {
    this.value = value;
  }

  public float getValue() {
    return value;
  }

}
