package com.ghertzsch.taxcalculator.domain.entities;

import com.ghertzsch.taxcalculator.domain.exceptions.DomainException;
import com.ghertzsch.taxcalculator.domain.valueobjects.Country;
import com.ghertzsch.taxcalculator.domain.valueobjects.GrossAmount;
import com.ghertzsch.taxcalculator.domain.valueobjects.NetAmount;
import com.ghertzsch.taxcalculator.domain.valueobjects.TaxType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.UUID;

public class NetAmountComputationTest {

  @Test
  public void ShouldHaveAnId() {
    // Arrange
    var netAmountComputationId = UUID.randomUUID();
    var netAmountComputation = new NetAmountComputation(netAmountComputationId);

    // Act
    var result = netAmountComputation.getId();

    // Assert
    Assertions.assertEquals(netAmountComputationId, result);
  }

  @Test
  public void ShouldHaveANetAmount() {
    // Arrange
    var netAmountComputationId = UUID.randomUUID();
    var netAmountComputation = new NetAmountComputation(netAmountComputationId);

    // Act
    var result = netAmountComputation.getNetAmount();

    // Assert
    Assertions.assertNotNull(result);
  }

  @Test
  public void SetNetAmountShouldSetIfPositiveValueIsPassed() throws DomainException {
    // Arrange
    var netAmountComputationId = UUID.randomUUID();
    var netAmountComputation = new NetAmountComputation(netAmountComputationId);

    var netAmount = new NetAmount(1);

    // Act
    netAmountComputation.setNetAmount(netAmount);
    var result = netAmountComputation.getNetAmount();

    // Assert
    Assertions.assertEquals(netAmount, result);
  }

  @Test
  public void SetNetAmountShouldNotSetAndThrowADomainExceptionIfANegativeValueIsPassed() {
    // Arrange
    var netAmountComputationId = UUID.randomUUID();
    var netAmountComputation = new NetAmountComputation(netAmountComputationId);

    var netAmount = new NetAmount(-1);

    // Act
    Executable exec = () -> netAmountComputation.setNetAmount(netAmount);
    var result = netAmountComputation.getNetAmount();

    // Assert
    Assertions.assertNotEquals(netAmount, result);
    Assertions.assertThrows(DomainException.class, exec);
  }

  @Test
  public void ShouldHaveAGrossAmount() {
    // Arrange
    var netAmountComputationId = UUID.randomUUID();
    var netAmountComputation = new NetAmountComputation(netAmountComputationId);

    // Act
    var result = netAmountComputation.getGrossAmount();

    // Assert
    Assertions.assertNotNull(result);
  }

  @Test
  public void SetGrossAmountShouldSetIfPositiveValueIsPassed() throws DomainException {
    // Arrange
    var netAmountComputationId = UUID.randomUUID();
    var netAmountComputation = new NetAmountComputation(netAmountComputationId);

    var grossAmount = new GrossAmount(1);

    // Act
    netAmountComputation.setGrossAmount(grossAmount);
    var result = netAmountComputation.getGrossAmount();

    // Assert
    Assertions.assertEquals(grossAmount, result);
  }

  @Test
  public void SetGrossAmountShouldNotSetAndThrowADomainExceptionIfANegativeValueIsPassed() {
    // Arrange
    var netAmountComputationId = UUID.randomUUID();
    var netAmountComputation = new NetAmountComputation(netAmountComputationId);

    var grossAmount = new GrossAmount(-1);

    // Act
    Executable exec = () -> netAmountComputation.setGrossAmount(grossAmount);
    var result = netAmountComputation.getGrossAmount();

    // Assert
    Assertions.assertNotEquals(grossAmount, result);
    Assertions.assertThrows(DomainException.class, exec);
  }

  @Test
  public void ShouldNotHaveATaxRate() {
    // Arrange
    var netAmountComputationId = UUID.randomUUID();
    var netAmountComputation = new NetAmountComputation(netAmountComputationId);

    // Act
    var result = netAmountComputation.getTaxRate();

    // Assert
    Assertions.assertNull(result);
  }

  @Test
  public void SetTaxRateShouldSetIfAValidTaxRateIsPassed() {
    // Arrange
    var netAmountComputationId = UUID.randomUUID();
    var netAmountComputation = new NetAmountComputation(netAmountComputationId);

    var taxRate = new TaxRate(
      Country.GERMANY,
      TaxType.VALUE_ADDED_TAX,
      0.19f
    );

    // Act
    netAmountComputation.setTaxRate(taxRate);
    var result = netAmountComputation.getTaxRate();

    // Assert
    Assertions.assertEquals(taxRate, result);
  }

  @Test
  public void SetTaxRateShouldSetIfNullIsPassed() {
    // Arrange
    var netAmountComputationId = UUID.randomUUID();
    var netAmountComputation = new NetAmountComputation(netAmountComputationId);

    // Act
    netAmountComputation.setTaxRate(null);
    var result = netAmountComputation.getTaxRate();

    // Assert
    Assertions.assertNull(result);
  }

}
