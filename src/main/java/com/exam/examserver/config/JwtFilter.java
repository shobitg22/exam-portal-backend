package com.exam.examserver.config;

import com.exam.examserver.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    UserDetailsImpl userDetails;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        System.out.println(authorizationHeader);

        String userName=null;
        String token = null;
        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
            token=authorizationHeader.substring(7);
                userName = jwtUtils.extractUsername(token);
        }
        else {
            System.out.println("Header doesnot starts with bearer");
        }

        if(userName !=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails user =userDetails.loadUserByUsername(userName);
            System.out.println(user.getAuthorities());
            if(jwtUtils.validateToken(token,user)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }
    filterChain.doFilter(request,response);
    }
}
