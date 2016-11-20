package com.cartracking.main.security.handler;

import com.cartracking.main.security.jwt.JwtTokenService;
import com.cartracking.main.security.model.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationSuccess extends SimpleUrlAuthenticationSuccessHandler {

    private JwtTokenService jwtTokenService;

    @Autowired
    public AuthenticationSuccess(JwtTokenService tokenService) {
        this.jwtTokenService = tokenService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_OK);

        SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();
        response.getWriter().write(jwtTokenService.generateToken(userDetails));
    }
}
