package com.gruporyc.restaurant.kitchen.handlers;

import com.gruporyc.restaurant.kitchen.utilities.Template;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.HttpClientErrorException;
import spark.Route;

import static com.gruporyc.restaurant.kitchen.utilities.ModelEntry.withModel;
import static com.gruporyc.restaurant.kitchen.utilities.Template.render;

public class ExceptionHandler {
    private final static Logger LOGGER = LogManager.getLogger(ExceptionHandler.class);

    public ExceptionHandler() {}

    public Route showError(HttpClientErrorException ex) {
        return (req, resp) -> {
            LOGGER.error(ex);
            return render(req, Template.ERROR_PAGE,
                    withModel("error", true),
                    withModel("errorCode", ex.getCause().getMessage()));
        };
    }
}
