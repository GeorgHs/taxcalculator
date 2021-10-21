package com.ghertzsch.taxcalculator.plugins.endpoints;

import com.ghertzsch.taxcalculator.adapters.ListNetAmountComputationsDTO;
import com.ghertzsch.taxcalculator.adapters.NetAmountComputationMapper;
import com.ghertzsch.taxcalculator.application.queries.ListNetAmountComputationsHandler;
import com.ghertzsch.taxcalculator.domain.queries.ListNetAmountComputations;
import com.ghertzsch.taxcalculator.domain.repositories.NetAmountComputationRepository;
import com.google.gson.Gson;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.stream.Collectors;

public class ListNetAmountComputationsEndpoint {

  private final NetAmountComputationRepository repository;

  public ListNetAmountComputationsEndpoint(NetAmountComputationRepository netAmountComputationRepository) {
    this.repository = netAmountComputationRepository;
  }

  public Router getRouter(Vertx vertx) {
    var router = Router.router(vertx);
    router.route("/computations*").handler(BodyHandler.create());
    router.get("/computations").handler(this::endpoint);

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

    var dto = new ListNetAmountComputationsDTO(
      Integer.parseInt(skipParam),
      Integer.parseInt(limitParam)
    );

    var query = new ListNetAmountComputations(
      dto.getSkip(),
      dto.getLimit()
    );

    var queryHandler = new ListNetAmountComputationsHandler(
      repository
    );

    var queryResult = queryHandler.handle(query);

    var mapper = new NetAmountComputationMapper();
    var result = queryResult.stream()
      .map(mapper)
      .collect(Collectors.toList());

    context.response().setStatusCode(HttpResponseStatus.OK.code())
      .putHeader("content-type", "application/json")
      .end(new Gson().toJson(result));
  }

}
