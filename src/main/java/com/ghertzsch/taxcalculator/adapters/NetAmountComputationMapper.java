package com.ghertzsch.taxcalculator.adapters;

import com.ghertzsch.taxcalculator.domain.entities.NetAmountComputation;

import java.util.function.Function;

public class NetAmountComputationMapper implements Function<NetAmountComputation, NetAmountComputationDTO> {
  @Override
  public NetAmountComputationDTO apply(NetAmountComputation netAmountComputation) {
    return new NetAmountComputationDTO(
      netAmountComputation.getId().toString(),
      netAmountComputation.getGrossAmount().getValue(),
      netAmountComputation.getTaxRate().getCountry().name(),
      netAmountComputation.getTaxRate().getTaxType().name(),
      netAmountComputation.getTaxRate().getValue(),
      netAmountComputation.getNetAmount().getValue()
    );
  }
}
