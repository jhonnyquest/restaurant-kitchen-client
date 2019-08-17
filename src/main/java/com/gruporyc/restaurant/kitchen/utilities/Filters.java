package com.gruporyc.restaurant.kitchen.utilities;

import spark.Request;
import spark.Response;

import static com.gruporyc.restaurant.kitchen.utilities.SessionUtil.Attributes.LANGUAGE;

public class Filters {

    public static void captureLanguage(Request request, Response response) {
        String language = request.queryParams(LANGUAGE);
        if (language != null) {
            request.session().attribute(LANGUAGE, language);
            response.redirect(request.pathInfo());
        }
    }

    public static void checkNotFound(Request request, Response response) {
        if (!request.pathInfo().equals("/login")) {
            return;
        }
    }
}