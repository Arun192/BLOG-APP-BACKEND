package com.arun.blog.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenHelper {

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	private String secret = "jwtTokenKey";
	
	// Retrive username from jwt token
	public String getUsernameFromToken(String token)
	{
		return getClaimFromToken(token , Claims::getSubject);
	}

	//retrive Expiration date from jwt token
	public Date getExpirationDateFromToken(String token)
	{
		return getClaimFromToken(token , Claims::getExpiration);
		
	}
	
	public <T> T getClaimFromToken(String token , Function<Claims , T> claimResolver)
	{
		final Claims claims = getAllClaimsFromToken(token);
		return claimResolver.apply(claims);
	}
	
	//for retriveveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	// check if the token has expired
	public Boolean isTokenExpired(String token)
	{
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	// Generate token for User
	public String generateToken(UserDetails userDetails)
	{
		Map<String, Object> claims = new HashMap<String, Object>();
		return doGenerateToken(claims , userDetails.getUsername());
	}

	//While Creating the token
		//1. Define Claims of token , like Issuer , expiration , Subject and the Id
		//2.Sign the JWT using the HS512 algorithm and Secret Key
		//3. According to JWS Compact Serialisation(https://tools.ietf.org/html/draft-ietf-jose-json
		//compaction of the JWT to a URL-safe String
	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();

		
	}
	//validate token
	public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
	
}

