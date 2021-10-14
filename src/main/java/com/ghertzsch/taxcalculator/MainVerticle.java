package com.ghertzsch.taxcalculator;

import com.ghertzsch.taxcalculator.application.NetAmountComputedHandler;
import com.ghertzsch.taxcalculator.domain.entities.TaxRate;
import com.ghertzsch.taxcalculator.domain.events.NetAmountComputed;
import com.ghertzsch.taxcalculator.domain.repositories.TaxRateRepository;
import com.ghertzsch.taxcalculator.domain.valueobjects.Country;
import com.ghertzsch.taxcalculator.domain.valueobjects.GrossAmount;
import com.ghertzsch.taxcalculator.domain.valueobjects.TaxType;
import com.ghertzsch.taxcalculator.plugins.InMemoryNetAmountComputationRepository;
import com.ghertzsch.taxcalculator.plugins.InMemoryTaxRateRepository;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

/*public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.createHttpServer().requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from Vert.x!");
    }).listen(8888, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }
}*/

public class MainVerticle {

  public static void main(String[] args) {

    var taxRate = new TaxRate(Country.DENMARK, TaxType.VALUE_ADDED_TAX, (float) 0.25);

    var netAmountComputationRepo = new InMemoryNetAmountComputationRepository();

    var event = new NetAmountComputed(taxRate, new GrossAmount((float) 100_000));
    var eventHandler = new NetAmountComputedHandler(netAmountComputationRepo);
    eventHandler.handle(event);

    netAmountComputationRepo.findAllComputations().forEach(netAmountComputation ->
      System.out.println(netAmountComputation.getId() + ": " + netAmountComputation.getNetAmount().getValue()));
  }

}
