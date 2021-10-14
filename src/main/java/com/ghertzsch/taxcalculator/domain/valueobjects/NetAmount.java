package com.ghertzsch.taxcalculator.domain.valueobjects;

public class NetAmount {

  private final float value;

  public NetAmount(float value) {
    this.value = value;
  }

  public float getValue() {
    return value;
  }

}
