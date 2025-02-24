package com.notifications.streaming.Filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notifications.streaming.dto.UserCredentials;
import com.notifications.streaming.dto.UserDto;
import com.notifications.streaming.models.UserAuthentication;
import com.notifications.streaming.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class TokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    Logger logger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("token");
        if (token.isEmpty() || token.isBlank()) {
            return null;
        }

        UserCredentials userCredentials = null;
        try {
            userCredentials = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        UserAuthentication userAuthentication = userService.validateUser(userCredentials.getUsername(), userCredentials.getPassword());
        if (userAuthentication.getToken().isBlank() || userAuthentication.getToken().isEmpty()) {
            return null;
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userAuthentication.getUsername(), userAuthentication.getToken(), new ArrayList<>()
        );
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String token = ((UserAuthentication) authResult).getToken();
        response.addHeader("Authorization", "Bearer " + token);
    }

}
