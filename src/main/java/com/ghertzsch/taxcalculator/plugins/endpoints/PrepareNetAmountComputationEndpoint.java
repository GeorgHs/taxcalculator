package com.ghertzsch.taxcalculator.plugins.endpoints;

import com.ghertzsch.taxcalculator.application.commands.PrepareNetAmountComputationHandler;
import com.ghertzsch.taxcalculator.domain.commands.PrepareNetAmountComputation;
import com.ghertzsch.taxcalculator.domain.repositories.NetAmountComputationRepository;
import com.ghertzsch.taxcalculator.domain.repositories.TaxRateRepository;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class PrepareNetAmountComputationEndpoint {

  private final NetAmountComputationRepository netAmountComputationRepository;

  public PrepareNetAmountComputationEndpoint(NetAmountComputationRepository netAmountComputationRepository) {
    this.netAmountComputationRepository = netAmountComputationRepository;
  }

  public Router getRouter(Vertx vertx) {
    var router = Router.router(vertx);
    router.route("/computations*").handler(BodyHandler.create());
    router.post("/computations").handler(this::endpoint);

    return router;
  }

  private void endpoint(RoutingContext context) {
    var prepareNetAmountComputation = new PrepareNetAmountComputation();
    var prepareNetAmountComputationHandler = new PrepareNetAmountComputationHandler(netAmountComputationRepository);

    prepareNetAmountComputationHandler.handle(prepareNetAmountComputation);

    context.response().setStatusCode(HttpResponseStatus.ACCEPTED.code()).end();
  }

}
