package com.devs.travels.config.security;


import com.devs.travels.domain.user.UserPrincipal;
import com.devs.travels.repository.UserRepository;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

	private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expirationInMs}")
	private int expirationInMs;

	@Autowired
	UserRepository userRepository;

	public String generateJwt(Authentication authentication) {

		String idUser = null;
		try {
			UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
			idUser = Long.toString(userPrincipal.getId());
		} catch (Exception e) {

			// TODO: handle exception
		}

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + expirationInMs);

		return Jwts.builder()
				.setSubject(idUser)
				.setIssuedAt(new Date())
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	Long getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(token)
				.getBody();

		return Long.parseLong(claims.getSubject());
	}

	boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException ex) {
			logger.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			logger.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			logger.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			logger.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			logger.error("JWT claims string is empty.");
		}
		return false;
	}
}