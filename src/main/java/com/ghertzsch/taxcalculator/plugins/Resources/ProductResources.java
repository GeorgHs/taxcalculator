package com.ghertzsch.taxcalculator.plugins.Resources;

import com.google.gson.Gson;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import com.fasterxml.jackson.core.*;

import java.util.ArrayList;
import java.util.List;

public class ProductResources {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductResources.class);


	public Router getAPISubRouter(Vertx vertx) {

		Router apiSubRouter = Router.router(vertx);

		// API routing
		//apiSubRouter.route("/*").handler(this::defaultProcessorForAllAPI2);

		apiSubRouter.route("/v1/products*").handler(BodyHandler.create());
		apiSubRouter.get("/v1/products").handler(this::getAllProducts);
		apiSubRouter.get("/v1/products/:id").handler(this::getProductById);
		apiSubRouter.post("/v1/products").handler(this::addProduct);
		apiSubRouter.put("/v1/products/:id").handler(this::updateProductById);
		apiSubRouter.delete("/v1/products/:id").handler(this::deleteProductById);

		return apiSubRouter;
	}


	// Get all products as array of products
	public void getAllProducts(RoutingContext routingContext) {

		JsonObject responseJson = new JsonObject();
		Product firstItem = new Product("112233", "123", "My item 123");
		Product secondItem = new Product("11334455", "321", "My item 321");

		List<Product> products = new ArrayList<Product>();

		products.add(firstItem);
		products.add(secondItem);

    Gson gson = new Gson();
    String json = gson.toJson(products);

		responseJson.put("products", products);

		routingContext.response()
			.setStatusCode(200)
			.putHeader("content-type", "application/json")
			.end(json);
	}

	// Get one products that matches the input id and return as single json object
	public void getProductById(RoutingContext routingContext) {

		final String productId = routingContext.request().getParam("id");

		String number = "123";

		Product firstItem = new Product(productId, number, "My item " + number);

		Gson gson = new Gson();
		String json = gson.toJson(firstItem);

		routingContext.response()
		.setStatusCode(200)
		.putHeader("content-type", "application/json")
		.end(json);

	}

	// Insert one item passed in from the http post body return what was added with unique id from the insert
	public void addProduct(RoutingContext routingContext) {

		JsonObject jsonBody = routingContext.getBodyAsJson();

		System.out.println(jsonBody);

		String number = jsonBody.getString("number");
		String description = jsonBody.getString("description");

		Product newItem = new Product("", number, description);



		// Add into database and get unique id
		newItem.setId("556677");

    Gson gson = new Gson();
    String json = gson.toJson(newItem);

		routingContext.response()
		.setStatusCode(201)
		.putHeader("content-type", "application/json")
		.end(json);


	}

	// Update the item based on the url product id and return update product info
	public void updateProductById(RoutingContext routingContext) {

		final String productId = routingContext.request().getParam("id");

		JsonObject jsonBody = routingContext.getBodyAsJson();


		String number = jsonBody.getString("number");
		String description = jsonBody.getString("description");

		Product updatedItem = new Product(productId, number, description);

    Gson gson = new Gson();
    String json = gson.toJson(updatedItem);

		routingContext.response()
		.setStatusCode(200)
		.putHeader("content-type", "application/json")
		.end(json);


	}

	// Delete item and return 200 on success or 400 on fail
	public void deleteProductById(RoutingContext routingContext) {

		final String productId = routingContext.request().getParam("id");

		routingContext.response()
		.setStatusCode(200)
		.putHeader("content-type", "application/json")
		.end();


	}

}
