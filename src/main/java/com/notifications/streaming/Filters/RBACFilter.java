package com.notifications.streaming.Filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@Component
public class RBACFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(RBACFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException, java.io.IOException {

        logger.debug("Inside filter function");

        // HttpRequest httprequest = (HttpRequest) servletRequest;

        // logger.info("This is the request: " + httprequest.getHeaders());
        chain.doFilter(servletRequest, servletResponse);

    }

}
