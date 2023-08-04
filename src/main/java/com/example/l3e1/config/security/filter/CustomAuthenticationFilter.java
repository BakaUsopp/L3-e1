package com.example.l3e1.config.security.filter;


import com.example.l3e1.config.security.authentication.CustomAuthentication;
import com.example.l3e1.config.security.managers.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {



    private final CustomAuthenticationManager customAuthenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String key = String.valueOf(request.getHeader("key"));
        CustomAuthentication ca =new CustomAuthentication(false,key);

        var a =customAuthenticationManager.authenticate(ca);
        if (a.isAuthenticated()){

            SecurityContextHolder.getContext().setAuthentication(a);
            filterChain.doFilter(request, response); //Only When Authentication Worked
        }
    }
}
