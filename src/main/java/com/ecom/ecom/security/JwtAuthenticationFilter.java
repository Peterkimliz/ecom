package com.ecom.ecom.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final UserDetailsImplementationService userDetailsImplementation;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        try {
            String token = "";
            String username = "";
            String authHeader = request.getHeader("Authorization");

           
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403 Forbidden
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Authorization token is missing.\"}");
                return;
            }

    
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);

            // Validate the token and set authentication in context if valid
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsImplementation.loadUserByUsername(username);
                boolean isTokenValid = jwtService.isTokenValid(token, userDetails);
                if (isTokenValid) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }

            filterChain.doFilter(request, response);

        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            System.out.println("JWT expired: " + e.getMessage());
            handlerExceptionResolver.resolveException(request, response, null, e);
        } catch (io.jsonwebtoken.UnsupportedJwtException e) {
            System.out.println("Unsupported JWT: " + e.getMessage());
            handlerExceptionResolver.resolveException(request, response, null, e);
        } catch (io.jsonwebtoken.MalformedJwtException e) {
            System.out.println("Malformed JWT: " + e.getMessage());
            handlerExceptionResolver.resolveException(request, response, null, e);
        } catch (@SuppressWarnings("deprecation") io.jsonwebtoken.SignatureException e) {
            System.out.println("Invalid signature: " + e.getMessage());
            handlerExceptionResolver.resolveException(request, response, null, e);
        } catch (Exception e) {
            System.out.println("General error: " + e);
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }
}