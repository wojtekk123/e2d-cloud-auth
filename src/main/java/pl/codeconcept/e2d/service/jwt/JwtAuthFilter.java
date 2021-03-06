package pl.codeconcept.e2d.service.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

public class JwtAuthFilter extends BasicAuthenticationFilter {

    private final JwtToken jwtToken;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);

    public JwtAuthFilter(AuthenticationManager authenticationManager, JwtToken jwtToken) {
        super(authenticationManager);
        this.jwtToken = jwtToken;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try {
            String jwt = getJwt(httpServletRequest);

            if (jwt != null) {

                String username = jwtToken.getClaims(jwt).getSubject();
                String role = jwtToken.getClaims(jwt).get("role").toString();
                Set<SimpleGrantedAuthority> grantedAuthority = Collections.singleton(new SimpleGrantedAuthority(role));
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, grantedAuthority);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

            filterChain.doFilter(httpServletRequest, httpServletResponse);

        } catch (MalformedJwtException e) {
            logger.error("Invalid token");
        } catch (ExpiredJwtException e) {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            logger.error("Token expired ");
        } catch (UsernameNotFoundException e) {
            logger.error("User not found");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Some other exception in JWT parsing");
        }
    }

    private String getJwt(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            return token.replace("Bearer ", "");
        }

        return null;
    }


}
