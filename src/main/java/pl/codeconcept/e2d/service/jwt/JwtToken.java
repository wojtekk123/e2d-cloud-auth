package pl.codeconcept.e2d.service.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import pl.codeconcept.e2d.database.entity.UserRegistration;
import pl.codeconcept.e2d.database.entity.enums.RoleType;

import java.util.Date;
import java.util.List;

@Component
public class JwtToken {

    private static final String jwtSecret = "1234";
    private static final int jwtExpiration = 12000000;


    public String generateJwtToken(UserRegistration principal) {
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .claim("role", principal.getRole())
                .claim("id", principal.getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

    }

    Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }

}
