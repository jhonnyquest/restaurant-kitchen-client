package com.gruporyc.restaurant.kitchen.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.gruporyc.restaurant.kitchen.utilities.SessionUtil.Attributes.LANGUAGE;

@Component
public class RequestInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String language = request.getParameter(LANGUAGE);
        if (language != null) {
            request.getSession().setAttribute(LANGUAGE, language);
        }
        return true;
    }
    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception exception) throws Exception {}
}