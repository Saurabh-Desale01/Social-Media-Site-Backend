package com.example.Social.Media.Site.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {
	
	private static SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstants.SECRET_KEY.getBytes());
	
	public static String generateToken(Authentication auth) {
		
		String jwt = Jwts.builder()
				.setIssuer("SocialSite").setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime()+86400000))
				.claim("email", auth.getName())
				.signWith(secretKey)
				.compact();
		
		return jwt;
	}
	
	public static String getEmailFromJwtToken(String jwt) {
		
		jwt = jwt.substring(7);
		
		Claims claims = Jwts.parser()
				.setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();
		
		String email = String.valueOf(claims.get("email"));
		
		return email;
	}
}
