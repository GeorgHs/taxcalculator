package com.ghertzsch.taxcalculator.plugins.endpoints;

import com.ghertzsch.taxcalculator.adapters.ListNetAmountComputationsDTO;
import com.ghertzsch.taxcalculator.adapters.NetAmountComputationMapper;
import com.ghertzsch.taxcalculator.adapters.PrepareNetAmountComputationDTO;
import com.ghertzsch.taxcalculator.application.commands.PrepareNetAmountComputationHandler;
import com.ghertzsch.taxcalculator.application.queries.ListNetAmountComputationsHandler;
import com.ghertzsch.taxcalculator.domain.commands.PrepareNetAmountComputation;
import com.ghertzsch.taxcalculator.domain.queries.ListNetAmountComputations;
import com.ghertzsch.taxcalculator.domain.repositories.NetAmountComputationRepository;
import com.ghertzsch.taxcalculator.domain.repositories.TaxRateRepository;
import com.google.gson.Gson;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import java.util.UUID;
import java.util.stream.Collectors;

public class PrepareNetAmountComputationEndpoint {

  private final TaxRateRepository taxRateRepository;

  private final NetAmountComputationRepository netAmountComputationRepository;

  public PrepareNetAmountComputationEndpoint(TaxRateRepository taxRateRepository, NetAmountComputationRepository netAmountComputationRepository) {
    this.taxRateRepository = taxRateRepository;
    this.netAmountComputationRepository = netAmountComputationRepository;
  }

  public Router getRouter(Vertx vertx) {
    var router = Router.router(vertx);
    router.route("/computations*").handler(BodyHandler.create());
    router.post("/computations").handler(this::endpoint);

    return router;
  }

  private void endpoint(RoutingContext context) {
    var request = context.request();
    var body = context.getBodyAsString();

    var dto = new Gson().fromJson(body, PrepareNetAmountComputationDTO.class);

    var command = new PrepareNetAmountComputation(
      UUID.fromString(dto.getTaxRateId()),
      dto.getGrossAmount()
    );

    var commandHandler = new PrepareNetAmountComputationHandler(
      taxRateRepository,
      netAmountComputationRepository
    );

    commandHandler.handle(command);

    context.response().setStatusCode(HttpResponseStatus.ACCEPTED.code()).end();
  }

}
