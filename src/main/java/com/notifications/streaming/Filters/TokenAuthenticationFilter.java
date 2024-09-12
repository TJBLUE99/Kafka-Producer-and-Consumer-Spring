package com.notifications.streaming.Filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@Component
public class TokenAuthenticationFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException, java.io.IOException {

        logger.debug("Inside filter function");

        chain.doFilter(servletRequest, servletResponse);

    }

}
