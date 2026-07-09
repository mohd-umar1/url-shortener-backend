package com.url.shortener.jwt;

import com.url.shortener.service.UserDetailsImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsImpl UserDetailsImpl;
    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        try {

            String token = jwtUtils.get_jwt_from_header(request); //get jwt token from header

            if(token!=null && jwtUtils.validate_token(token)) { //validate jwt token

                String username = jwtUtils.getusername(token);

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if(userDetails!=null) {
                    UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    // Spring Security Authentication object
                    // Stores current user and roles
                    authtoken.setDetails(
                            new WebAuthenticationDetailsSource()// Creates object that extracts request details
                            .buildDetails(request)// Extracts IP, session ID, etc. from request
                             );
                    SecurityContextHolder.getContext().setAuthentication(authtoken);
                    // Stores authentication information for the current request
                }

            }//get username from the token if valid

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        filterChain.doFilter(request, response);

    }
}
