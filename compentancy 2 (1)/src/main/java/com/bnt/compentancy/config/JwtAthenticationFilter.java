package com.bnt.compentancy.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bnt.compentancy.service.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String requestTokenHeader = request.getHeader("Authorization");
		
		String username =  null;
		String jwtToken = null;
		
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			try {
				jwtToken = requestTokenHeader.substring(7);
				username = this.jwtUtil.extractUsername(jwtToken);
		
			}catch(ExpiredJwtException ex){
				System.out.println("Jwt token has expired");
				ex.printStackTrace();
			}catch(Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}
	
		}else {
			System.out.println("jwt token does not starts with bearer");
		}
		
		
		//Validation
		if(username != null  && SecurityContextHolder.getContext().getAuthentication() == null) {
		final UserDetails	userDetails	=this.userDetailsService.loadUserByUsername(username);
		if(this.jwtUtil.validateToken(jwtToken, userDetails)) {
			
			UsernamePasswordAuthenticationToken userNamePasswordAuthenticationtoken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
			userNamePasswordAuthenticationtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));;
			//valid token
			SecurityContextHolder.getContext().setAuthentication(userNamePasswordAuthenticationtoken);
		 }
		}else {
			System.out.println("Token is not valid");
		}
		
		filterChain.doFilter(request, response);
		
	}

}
