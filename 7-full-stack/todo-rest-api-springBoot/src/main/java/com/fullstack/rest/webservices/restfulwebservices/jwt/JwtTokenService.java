package com.fullstack.rest.webservices.restfulwebservices.jwt;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JwtTokenService {
    
    private final JwtEncoder jwtEncoder;

    public JwtTokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public String generateToken(Authentication authentication) {
        var claims = JwtClaimsSet.builder()
                .issuer("self") // Issuer of the token, can be your application name
                .issuedAt(Instant.now()) // Token issued now
                .expiresAt(Instant.now().plus(30, ChronoUnit.MINUTES)) // Token valid for 30 minutes
                .subject(authentication.getName()) // Subject is the username
                .claim("scope", createScope(authentication)) // Custom claim for user roles/authorities
                .build(); // Build the JWT claims set

        // Encode the JWT claims set into a token
        // The JwtEncoderParameters is used to pass the claims to the encoder
        // The getTokenValue() method retrieves the actual JWT token string
        // This token can then be returned to the client for authentication
        // and used in subsequent requests to access protected resources
        return this.jwtEncoder
                .encode(JwtEncoderParameters.from(claims))
                .getTokenValue();
    }

    // get scope from authentication (user roles/authorities)
    private String createScope(Authentication authentication) {
        return authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
    }
}