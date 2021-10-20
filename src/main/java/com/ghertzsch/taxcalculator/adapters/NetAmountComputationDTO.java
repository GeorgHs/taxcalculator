package com.ghertzsch.taxcalculator.adapters;

import com.ghertzsch.taxcalculator.domain.entities.NetAmountComputation;

import java.util.UUID;

/**
 * das ist das POJO zur Ausgabe einer einzelnen Computation. Das sind die Felder des JSONs
 * DTO = Resource (synonym)
 */
public class NetAmountComputationDTO {
  private final String id;
  private final float grossAmount;
  private final String country;
  private final String taxType;
  private final float taxRate;
  private final float netAmount;

  public NetAmountComputationDTO(String id, float grossAmount, String country, String taxType, float taxRate, float netAmount) {
    this.id = id;
    this.grossAmount = grossAmount;
    this.country = country;
    this.taxType = taxType;
    this.taxRate = taxRate;
    this.netAmount = netAmount;
  }

  public String getId() {
    return id;
  }

  public float getGrossAmount() {
    return grossAmount;
  }

  public String getCountry() {
    return country;
  }

  public String getTaxType() {
    return taxType;
  }

  public float getTaxRate() {
    return taxRate;
  }

  public float getNetAmount() {
    return netAmount;
  }
}

