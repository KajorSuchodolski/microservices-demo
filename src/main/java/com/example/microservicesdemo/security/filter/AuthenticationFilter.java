package com.example.microservicesdemo.security.filter;

import com.example.microservicesdemo.dto.TokenResponse;
import com.example.microservicesdemo.exception.InvalidCredentialsException;
import com.example.microservicesdemo.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException();
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();

        String login = user.getUsername();
        Set<String> roles = Set.of(user.getAuthorities()
                .toString().replace("[", "").replace("]", "").split(","));

        String token = JwtUtil.generateJwtToken(login, roles);
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setJwt(token);

        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getWriter(), tokenResponse);
    }
}
