package com.redis.rate.limiter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RateLimitFilter extends OncePerRequestFilter {

    private final FixedWindowRateLimiterService limiter;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws IOException, ServletException {

        String user = request.getHeader("X-USER-ID");

        System.out.println("doFilterInternal");

        if (!limiter.allowRequest(user)) {

            response.setStatus(429);
            response.getWriter().write("Too Many Requests");
            return;
        }

        filterChain.doFilter(request, response);

    }

}
