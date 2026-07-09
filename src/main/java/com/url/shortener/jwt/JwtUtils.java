package com.url.shortener.jwt;

import com.url.shortener.service.UserDetailsImpl;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.Column;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

      @Value("${jwt.secret}")
      private String jwtSecret;

      @Value("${jwt.expiration}") //used to inject values from application.properties
      private int jwtExpiration;


       public String get_jwt_from_header(HttpServletRequest request){
           String bearer_token = request.getHeader("Authorization");
           if(bearer_token!=null && bearer_token.startsWith("Bearer")){
               return bearer_token.substring(7);
           }
           return null;
       }

       public String generate_jwt(UserDetailsImpl userDetails){
             String Username = userDetails.getUsername();
             String Roles = userDetails
                     .getAuthorities()
                     .stream()
                     .map(authority->authority.getAuthority())
                     .collect(Collectors.joining(","));

             return Jwts.builder()
                     .setSubject(Username)
                     .claim("Roles",Roles)
                     .issuedAt(new Date())
                     .expiration(new Date(new Date().getTime()+ jwtExpiration))
                     .signWith(key())
                     .compact();
       }

       public String getusername(String token){

           return Jwts.parser()
                   .verifyWith((SecretKey) key())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload()
                   .getSubject();

       }
       public boolean validate_token(String token){
           try {
               Jwts.parser()
                       .verifyWith((SecretKey) key())
                       .build()
                       .parseSignedClaims(token);
               return true;
           } catch (JwtException e) {
               throw new RuntimeException(e);
           } catch (IllegalArgumentException e) {
               throw new RuntimeException(e);
           }
           catch (Exception e) {
               throw new RuntimeException(e);
           }
       }

       private Key key(){

           return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
       }
}
