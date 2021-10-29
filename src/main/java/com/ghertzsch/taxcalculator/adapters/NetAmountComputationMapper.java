package com.ghertzsch.taxcalculator.adapters;

import com.ghertzsch.taxcalculator.domain.entities.NetAmountComputation;

import java.util.function.Function;

public class NetAmountComputationMapper implements Function<NetAmountComputation, NetAmountComputationDTO> {
  @Override
  public NetAmountComputationDTO apply(NetAmountComputation netAmountComputation) {
    String  county = null;
    String  taxType = null;
    float  taxRate = 0;

    if (netAmountComputation.getTaxRate() != null) {
      county = netAmountComputation.getTaxRate().getCountry().name();
      taxType = netAmountComputation.getTaxRate().getTaxType().name();
      taxRate = netAmountComputation.getTaxRate().getValue();
    }

    return new NetAmountComputationDTO(
      netAmountComputation.getId().toString(),
      netAmountComputation.getGrossAmount().getValue(),
      county,
      taxType,
      taxRate,
      netAmountComputation.getNetAmount().getValue()
    );
  }
}
