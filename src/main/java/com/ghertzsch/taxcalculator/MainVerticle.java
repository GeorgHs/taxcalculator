package com.ghertzsch.taxcalculator;

//import io.vertx.config.ConfigRetriever;

import com.ghertzsch.taxcalculator.domain.entities.NetAmountComputation;
import com.ghertzsch.taxcalculator.domain.entities.TaxRate;
import com.ghertzsch.taxcalculator.domain.repositories.NetAmountComputationRepository;
import com.ghertzsch.taxcalculator.domain.repositories.TaxRateRepository;
import com.ghertzsch.taxcalculator.domain.valueobjects.Country;
import com.ghertzsch.taxcalculator.domain.valueobjects.GrossAmount;
import com.ghertzsch.taxcalculator.domain.valueobjects.NetAmount;
import com.ghertzsch.taxcalculator.domain.valueobjects.TaxType;
import com.ghertzsch.taxcalculator.plugins.database.InMemoryNetAmountComputationRepository;
import com.ghertzsch.taxcalculator.plugins.database.InMemoryTaxRateRepository;
import com.ghertzsch.taxcalculator.plugins.endpoints.ListNetAmountComputationsEndpoint;
import com.ghertzsch.taxcalculator.plugins.endpoints.PrepareNetAmountComputationEndpoint;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;



public class MainVerticle extends AbstractVerticle {

  private static final Logger LOGGER = LoggerFactory.getLogger(MainVerticle.class);

  private final NetAmountComputationRepository netAmountComputationRepository = new InMemoryNetAmountComputationRepository();
  private final TaxRateRepository taxRateRepository = new InMemoryTaxRateRepository();

  public static void main(String[] args) {


    Vertx vertx = Vertx.vertx();

    // Use config/config.json from resources/classpath
    /*ConfigRetriever configRetriever = ConfigRetriever.create(vertx);

    configRetriever.getConfig(config -> {

      if (config.succeeded()) {

        JsonObject configJson = config.result();

        System.out.println(configJson.encodePrettily());

        DeploymentOptions options = new DeploymentOptions().setConfig(configJson);
    vertx.deployVerticle(new MainVerticle(), options);*/
        vertx.deployVerticle(new MainVerticle());

      }



  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    Router router = Router.router(vertx);

    taxRateRepository.storeTaxRate(new TaxRate(
      Country.DENMARK,
      TaxType.VALUE_ADDED_TAX,
      25.0f
    ));
    taxRateRepository.findAllTaxRates().stream().map(taxRate -> taxRate.getId()).forEach(System.out::println);

    var listAmountComputationsEndpoint = new ListNetAmountComputationsEndpoint(
      netAmountComputationRepository
    );
    router.mountSubRouter("/api/", listAmountComputationsEndpoint.getRouter(vertx));

    var prepareNetAmountComputationEndpoint = new PrepareNetAmountComputationEndpoint(
      taxRateRepository,
      netAmountComputationRepository
    );
    router.mountSubRouter("/api/", prepareNetAmountComputationEndpoint.getRouter(vertx));

    router.route().handler(StaticHandler.create().setCachingEnabled(false));

    vertx.createHttpServer().requestHandler(router).listen(8100, asyncResult -> {
      if (asyncResult.succeeded()) {
        LOGGER.info("HTTP server running on port " + config().getInteger("http.port"));
      }
      else {
        LOGGER.error("Could not start a HTTP server", asyncResult.cause());
      }
    });
  }
}
