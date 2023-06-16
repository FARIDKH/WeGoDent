package com.company.WeGoDent.security;


import com.company.WeGoDent.security.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthTokenFilter  extends OncePerRequestFilter {


    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private UserService userService;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        Optional<String> jwt = resolveHeaderToken(request);
        if (jwt.isPresent() && tokenProvider.validateToken(jwt.get())) {

            String username = tokenProvider.extractUsername(jwt.get());
            if (StringUtils.isNotEmpty(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userService.loadUserByUsername(username);
                var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    private Optional<String> resolveHeaderToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        return StringUtils.isNotEmpty(bearerToken) && bearerToken.startsWith("Bearer ") ? Optional.of(bearerToken.substring(7)) : Optional.empty();
    }

}
