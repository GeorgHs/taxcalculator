package com.ghertzsch.taxcalculator.plugins.endpoints;

import com.ghertzsch.taxcalculator.adapters.ListTaxRatesDTO;
import com.ghertzsch.taxcalculator.adapters.TaxRateMapper;
import com.ghertzsch.taxcalculator.application.queries.ListNetAmountComputationsHandler;
import com.ghertzsch.taxcalculator.application.queries.ListTaxRatesHandler;
import com.ghertzsch.taxcalculator.domain.queries.ListTaxRates;
import com.ghertzsch.taxcalculator.domain.repositories.TaxRateRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.stream.Collectors;

public class ListTaxRatesEndpoint {

  private TaxRateRepository repository;

  public ListTaxRatesEndpoint(TaxRateRepository repository) {
    this.repository = repository;
  }

  public Router getRouter(Vertx vertx) {
    var router = Router.router(vertx);
    router.route("/taxrates*").handler(BodyHandler.create());
    router.get("/taxrates").handler(this::endpoint);

    return router;
  }

  private void endpoint(RoutingContext context) {
    var request = context.request();
    var queryParameters = request.params();

    var skipParam = queryParameters.get("skip");
    if (skipParam == null) {
      skipParam = "0";
    }

    var limitParam = queryParameters.get("limit");
    if (limitParam == null) {
      limitParam = "10";
    }

    var dto = new ListTaxRatesDTO(
      Integer.parseInt(skipParam),
      Integer.parseInt(limitParam)
    );

    var query = new ListTaxRates(
      dto.getSkip(),
      dto.getLimit()
    );

    var queryHandler = new ListTaxRatesHandler(
      repository
    );

    var queryResult = queryHandler.handle(query);

    var mapper = new TaxRateMapper();
    var result = queryResult.stream()
      .map(mapper)
      .collect(Collectors.toList());

    context.response().setStatusCode(HttpResponseStatus.OK.code())
      .putHeader("content-type", "application/json")
      .end(new GsonBuilder().serializeNulls().create().toJson(result));
  }
}
