package com.ghertzsch.taxcalculator.application.services;


import com.ghertzsch.taxcalculator.domain.entities.NetAmountComputation;
import com.ghertzsch.taxcalculator.domain.repositories.NetAmountComputationRepository;

public class NetAmountComputationService {
    private final NetAmountComputationRepository netAmountComputationRepository;
    private NetAmountComputation netAmountComputation;


    public NetAmountComputationService(NetAmountComputationRepository netAmountComputationRepository) {
      this.netAmountComputationRepository = netAmountComputationRepository;
    }
  }
