package com.cartracking.main.security.filter;

import com.cartracking.main.security.jwt.JwtTokenService;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

    private JwtTokenService tokenService;

    private UserDetailsService userDetailsService;

    public JwtAuthenticationTokenFilter(JwtTokenService tokenService, UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        String authToken = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (authToken == null) {
            chain.doFilter(req, res);
            return;
        }
        String token = authToken.substring("Bearer ".length());
        authenticate(token, httpRequest);

        chain.doFilter(req, res);
    }

    private void authenticate(String token, HttpServletRequest httpRequest) {
        String username;
        try {
            username = tokenService.getUsername(token);
        } catch (JwtException e) {
            return;
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (tokenService.isValid(userDetails, token)) {
            setAuthToken(userDetails, httpRequest);
        }
    }

    private void setAuthToken(UserDetails userDetails, HttpServletRequest httpRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
