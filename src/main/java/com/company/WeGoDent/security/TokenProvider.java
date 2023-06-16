package com.company.WeGoDent.security;


import com.company.WeGoDent.entity.User;
import dev.paseto.jpaseto.*;
import dev.paseto.jpaseto.lang.Keys;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {

    private final SecretKey secretKey;
    private final KeyPair keyPair;



    public TokenProvider() {
        secretKey = Keys.secretKey();
        keyPair = Keys.keyPairFor(Version.V2);
    }


    /**
     * Extract username from token string.
     *
     * @param token a valid token value
     * @return String username
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    private Instant extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).isBefore(Instant.now());
    }

    public Paseto parseToken(String token) {
        PasetoParser parser = Pasetos.parserBuilder()
                .setSharedSecret(secretKey)
                .setPublicKey(keyPair.getPublic())
                .build();

        return parser.parse(token);
    }

    public Claims getClaims(String token) {
        return parseToken(token).getClaims();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Generate token string.
     *
     * @param authentication the user
     * @return the string token generated
     */
    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        User userPrincipal = (User) authentication.getPrincipal();

        return Pasetos.V2.LOCAL.builder()
                .setSharedSecret(secretKey)
                .setIssuedAt(now)
                .setExpiration(now.plus(2, ChronoUnit.HOURS))
                .setSubject(userPrincipal.getUsername())
                .setKeyId(UUID.randomUUID().toString())
                .setAudience("wegodent.com")
                .setIssuer("dev.com")
                    .claim("aut", authorities)
                .toString();
    }


    /**
     * Validate token value.
     *
     * @param authToken String token
     * @return true if token is valid or false otherwise
     */
    public boolean validateToken(String authToken) {
        try {
            parseToken(authToken);
            return !isTokenExpired(authToken);
        } catch (PasetoException e) {
//            log.error("Token validation error: {}", e.getMessage());
            return false;
        }
    }
}
