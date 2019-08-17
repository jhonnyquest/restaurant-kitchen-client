package com.gruporyc.restaurant.kitchen;

import com.gruporyc.restaurant.kitchen.handlers.ExceptionHandler;
import com.gruporyc.restaurant.kitchen.handlers.OrderHandler;
import com.gruporyc.restaurant.kitchen.services.implementations.ApiGatewayManagerImpl;
import com.gruporyc.restaurant.kitchen.utilities.Filters;
import com.gruporyc.restaurant.kitchen.utilities.PropertyManager;
import com.gruporyc.restaurant.kitchen.utilities.Template;
import spark.Service;


import java.util.Properties;

import static com.gruporyc.restaurant.kitchen.utilities.ModelEntry.withModel;
import static com.gruporyc.restaurant.kitchen.utilities.Template.render;
import static spark.Spark.notFound;

public class RestaurantKitchenWebApplication {
	public static void main(String[] args) {
		PropertyManager pm = new PropertyManager();
		Properties instance = pm.getInstance();
		setupLoadsFe(Service.ignite(), Integer.valueOf(instance.getProperty("app.port")));
	}

	private static void setupLoadsFe(Service http, int port) {
		http.staticFiles.location("/public");

		OrderHandler orderHandler = new OrderHandler(new ApiGatewayManagerImpl());

		ExceptionHandler exceptionHandler = new ExceptionHandler();

		http.port(port);
		notFound((req, res) -> {
			res.type("text/html");
			String render = render(req, Template.ERROR_PAGE,
					withModel("error", true),
					withModel("errorCode", 404));
			return render;
		});

		http.before("*", Filters::captureLanguage);
		http.before("*", Filters::checkNotFound);

		http.path("/orders", () -> {
			http.get("/active", orderHandler.viewActiveOrders());
			http.get("/:orderId", orderHandler.getOrderById());
			http.post("/:orderId/item/:itemId", orderHandler.updateOrderItemStatus());
			http.post("/:orderId/status", orderHandler.updateOrderStatus());
		});
	}

}
