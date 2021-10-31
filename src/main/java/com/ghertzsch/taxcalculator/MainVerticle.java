package com.ghertzsch.taxcalculator;

//import io.vertx.config.ConfigRetriever;

import com.ghertzsch.taxcalculator.domain.repositories.NetAmountComputationRepository;
import com.ghertzsch.taxcalculator.domain.repositories.TaxRateRepository;
import com.ghertzsch.taxcalculator.plugins.Resources.GenerateAllCountriesWithTax;
import com.ghertzsch.taxcalculator.plugins.endpoints.ComputeNetAmountEndpoint;
import com.ghertzsch.taxcalculator.plugins.repositories.InMemoryNetAmountComputation;
import com.ghertzsch.taxcalculator.plugins.repositories.InMemoryTaxRate;
import com.ghertzsch.taxcalculator.plugins.endpoints.ListNetAmountComputationsEndpoint;
import com.ghertzsch.taxcalculator.plugins.endpoints.ListTaxRatesEndpoint;
import com.ghertzsch.taxcalculator.plugins.endpoints.PrepareNetAmountComputationEndpoint;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.StaticHandler;



public class MainVerticle extends AbstractVerticle {

  private static final Logger LOGGER = LoggerFactory.getLogger(MainVerticle.class);

  private final NetAmountComputationRepository netAmountComputationRepository = new InMemoryNetAmountComputation();
  private final TaxRateRepository taxRateRepository = new InMemoryTaxRate();

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

    router.route().handler(CorsHandler.create("http://localhost:8080")
      .allowedMethod(io.vertx.core.http.HttpMethod.GET)
      .allowedMethod(io.vertx.core.http.HttpMethod.POST)
      .allowedMethod(io.vertx.core.http.HttpMethod.OPTIONS)
      .allowCredentials(true)
      .allowedHeader("Access-Control-Allow-Headers")
      .allowedHeader("Authorization")
      .allowedHeader("Access-Control-Allow-Method")
      .allowedHeader("Access-Control-Allow-Origin")
      .allowedHeader("Access-Control-Allow-Credentials")
      .allowedHeader("Content-Type"));


      GenerateAllCountriesWithTax.generate(taxRateRepository);


    taxRateRepository.findAllTaxRates().stream().map(taxRate -> taxRate.getId()).forEach(System.out::println);

    var listTaxRatesEndpoint = new ListTaxRatesEndpoint(
      taxRateRepository
    );
    router.mountSubRouter("/api/", listTaxRatesEndpoint.getRouter(vertx));

    var listAmountComputationsEndpoint = new ListNetAmountComputationsEndpoint(
      netAmountComputationRepository
    );
    router.mountSubRouter("/api/", listAmountComputationsEndpoint.getRouter(vertx));

    var prepareNetAmountComputationEndpoint = new PrepareNetAmountComputationEndpoint(
      netAmountComputationRepository
    );
    router.mountSubRouter("/api/", prepareNetAmountComputationEndpoint.getRouter(vertx));

    var computeNetAmountEndpoint = new ComputeNetAmountEndpoint(
      taxRateRepository,
      netAmountComputationRepository
    );
    router.mountSubRouter("/api/", computeNetAmountEndpoint.getRouter(vertx));

    router.route().handler(StaticHandler.create().setCachingEnabled(false));

    vertx.createHttpServer().requestHandler(router).listen(1323, asyncResult -> {
      if (asyncResult.succeeded()) {
        LOGGER.info("HTTP server running on port " + String.valueOf(1323));
      }
      else {
        LOGGER.error("Could not start a HTTP server", asyncResult.cause());
      }
    });
  }
}
