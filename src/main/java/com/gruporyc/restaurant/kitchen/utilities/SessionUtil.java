package com.gruporyc.restaurant.kitchen.utilities;

import com.gruporyc.restaurant.kitchen.enums.Language;

import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Optional;

import static com.gruporyc.restaurant.kitchen.utilities.SessionUtil.Attributes.LANGUAGE;

public class SessionUtil {

    public interface Attributes {
        String LANGUAGE = "lang";
    }

    public static Locale getLocale(HttpSession session) {
        return Optional.ofNullable(session.getAttribute(LANGUAGE))
                .map(l -> new Locale((String) l))
                .orElse(new Locale.Builder().setLanguage(Language.ES.name()).build());
    }
}