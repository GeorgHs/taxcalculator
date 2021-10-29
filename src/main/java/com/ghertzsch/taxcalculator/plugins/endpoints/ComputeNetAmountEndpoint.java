package com.ghertzsch.taxcalculator.plugins.endpoints;

import com.ghertzsch.taxcalculator.adapters.ComputeNetAmountDTO;
import com.ghertzsch.taxcalculator.application.commands.ComputeNetAmountHandler;
import com.ghertzsch.taxcalculator.application.commands.PrepareNetAmountComputationHandler;
import com.ghertzsch.taxcalculator.domain.commands.ComputeNetAmount;
import com.ghertzsch.taxcalculator.domain.commands.PrepareNetAmountComputation;
import com.ghertzsch.taxcalculator.domain.repositories.NetAmountComputationRepository;
import com.ghertzsch.taxcalculator.domain.repositories.TaxRateRepository;
import com.google.gson.Gson;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class ComputeNetAmountEndpoint {

  private final TaxRateRepository taxRateRepository;

  private final NetAmountComputationRepository netAmountComputationRepository;

  public ComputeNetAmountEndpoint(TaxRateRepository taxRateRepository, NetAmountComputationRepository netAmountComputationRepository) {
    this.taxRateRepository = taxRateRepository;
    this.netAmountComputationRepository = netAmountComputationRepository;
  }

  public Router getRouter(Vertx vertx) {
    var router = Router.router(vertx);
    router.route("/computations*").handler(BodyHandler.create());
    router.post("/computations/:id/compute").handler(this::endpoint);

    return router;
  }

  private void endpoint(RoutingContext context) {
    var request = context.request();
    var body = context.getBodyAsString();

    var dto = new Gson().fromJson(body, ComputeNetAmountDTO.class);
    dto.setNetAmountComputationId(request.getParam("id"));

    var computeNetAmount = new ComputeNetAmount(dto.getNetAmountComputationId(), dto.getTaxRateId(), dto.getGrossAmount());
    var computeNetAmountHandler = new ComputeNetAmountHandler(
      taxRateRepository,
      netAmountComputationRepository
    );

    computeNetAmountHandler.handle(computeNetAmount);

    context.response().setStatusCode(HttpResponseStatus.ACCEPTED.code()).end();
  }

}
