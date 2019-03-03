package com.online.training.service;

import java.io.Serializable;

import com.online.training.model.Constants;
import com.online.training.table.model.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TokenProvider implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String getScopesFromToken(String token) {
        return getAllClaimsFromToken(token).get("scopes").toString();
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        logger.info("ALL CLAIMS = {}", claims);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(Constants.SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim(Constants.AUTHORITIES_KEY, user.getRole())
                .signWith(SignatureAlgorithm.HS256, Constants.SIGNING_KEY)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Constants.ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .compact();
    }

    public Boolean validateToken(String token, User userDetails) {

        final String username = getUsernameFromToken(token);
        final String role = getScopesFromToken(token);
        return (
                username.equals(userDetails.getUsername())
                        && !isTokenExpired(token)
        && userDetails.getRole().equalsIgnoreCase(role));
    }

    public UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth, final User userDetails) {

        final JwtParser jwtParser = Jwts.parser().setSigningKey(Constants.SIGNING_KEY);

        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);

        final Claims claims = claimsJws.getBody();

        final Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(Constants.AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    public String getUsernameFromSecurityContext() {
        String userInfo = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        //User{id=10, username='shantaram', password='$2a$10$k0REcEguyUZv5zqgmKb5g.ZeK5UxRItRVIABQVbzu3EvKp.H4Nhdq', role='professor'}
        userInfo = userInfo.substring(userInfo.indexOf("username"));
        int startIndex = userInfo.indexOf("=") + 2;
        int endIndex = userInfo.indexOf(",") - 1;
        String username = userInfo.substring(startIndex, endIndex);
        logger.info("USERNAME FROM SECURITY CONTEXT = {}" ,username);
        return username;
    }

    public long getIdFromSecurityContext() {
        String userInfo = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        //User{id=10, username='shantaram', password='$2a$10$k0REcEguyUZv5zqgmKb5g.ZeK5UxRItRVIABQVbzu3EvKp.H4Nhdq', role='professor'}
        userInfo = userInfo.substring(userInfo.indexOf("id="));
        int startIndex = userInfo.indexOf("=") + 1;
        int endIndex = userInfo.indexOf(",");
        String id = userInfo.substring(startIndex, endIndex);
        logger.info("ID FROM SECURITY CONTEXT = {}" ,id);
        return Long.parseLong(id);
    }
}
