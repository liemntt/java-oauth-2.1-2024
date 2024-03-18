package dev.thanhliem.oauth.security;

import dev.thanhliem.oauth.exceptions.ApplicationException;
import dev.thanhliem.oauth.properties.AuthenticateProperties;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final AuthenticateProperties properties;

    public String generate(String username) {
        var issuedAt = Date.from(Instant.now());
        var expiredAt = new Date(issuedAt.getTime() + properties.getExpiredTime());
        return Jwts.builder()
            .signWith(secretKey())
            .issuedAt(issuedAt)
            .expiration(expiredAt)
            .subject(username)
            .compact();
    }

    private SecretKey secretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(properties.getSecretKey()));
    }

    public String getUsername(String token) {
        return Jwts.parser()
            .verifyWith(secretKey())
            .build()
            .parseSignedClaims(token).getPayload().getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().verifyWith(secretKey())
                .build()
                .parse(token);
            return true;
        } catch (MalformedJwtException e) {
            throw new ApplicationException("Invalid token");
        } catch (ExpiredJwtException e) {
            throw new ApplicationException("Expired token");
        } catch (SignatureException e) {
            throw new ApplicationException("Cannot verify token");
        } catch (SecurityException e) {
            throw new ApplicationException("Unsupported token");
        } catch (IllegalArgumentException e) {
            throw new ApplicationException("Token is null or empty");
        }
    }
}
