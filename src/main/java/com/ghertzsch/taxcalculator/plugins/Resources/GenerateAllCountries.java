package com.ghertzsch.taxcalculator.plugins.Resources;

import com.ghertzsch.taxcalculator.domain.repositories.TaxRateRepository;
import com.ghertzsch.taxcalculator.plugins.Resources.Country.*;

public class GenerateAllCountries {
  public static void generate(TaxRateRepository taxRateRepository) {
    Austria.generate(taxRateRepository);
    Belgium.generate(taxRateRepository);
    Bulgaria.generate(taxRateRepository);
    Croatia.generate(taxRateRepository);
    Cyprus.generate(taxRateRepository);
    CzechRepublic.generate(taxRateRepository);
    Denmark.generate(taxRateRepository);
    Estonia.generate(taxRateRepository);
    Finnland.generate(taxRateRepository);
    France.generate(taxRateRepository);
    Germany.generate(taxRateRepository);
    Greece.generate(taxRateRepository);
    Hungary.generate(taxRateRepository);
    Ireland.generate(taxRateRepository);
    Italy.generate(taxRateRepository);
    Latvia.generate(taxRateRepository);
    Lithuania.generate(taxRateRepository);
    Luxemburg.generate(taxRateRepository);
    Malta.generate(taxRateRepository);
    Netherlands.generate(taxRateRepository);
    NorthernIreland.generate(taxRateRepository);
    Poland.generate(taxRateRepository);
    Portugal.generate(taxRateRepository);
    Romania.generate(taxRateRepository);
    Slovakia.generate(taxRateRepository);
    Spain.generate(taxRateRepository);
    Sweden.generate(taxRateRepository);
  }
}
