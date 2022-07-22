package com.example.accountservice.security.filter;

import com.example.accountservice.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Stream;

@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {

    private static final String BEARER = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith(BEARER)) {
            log.info("Invalid authorization header");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.substring(BEARER.length());

        if (!JwtUtil.verifyJwtToken(token)) {
            log.info("Invalid token provided");
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        Claims claims = JwtUtil.parseJwtToken(token);

        String login = claims.getSubject();
        String roles = claims.get("roles").toString();
        Collection<SimpleGrantedAuthority> authorities = Stream.of(roles
                        .replace("[", "")
                        .replace("]", "")
                        .split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(login, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
