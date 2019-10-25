package pl.codeconscept.e2d.cloudauth;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import pl.codeconcept.e2d.database.entity.UserActivity;
import pl.codeconcept.e2d.database.entity.Users;
import pl.codeconcept.e2d.database.repository.UserActivityRepo;
import pl.codeconcept.e2d.service.jwt.JwtToken;
import pl.codeconcept.e2d.service.user.UserActivityService;
import pl.codeconcept.e2d.service.user.UserSrvice;

import javax.swing.*;

public class JwtTokenTest {

    Users prinicple = new Users ("jacek","1234","ADMIN_ROLE");

    @Test
    public void schouldCheckToken () {
        JwtToken jwtToken = new JwtToken();
        String token = jwtToken.generateJwtToken(prinicple);
        Assert.assertNotNull(token);
    }

    @Test
    public void shouldStartWith (){

        JwtToken jwtToken = new JwtToken();
        String jwt=  jwtToken.generateJwtToken(prinicple);
        Assert.assertTrue(jwt.startsWith("eyJhbGciOiJIUzUxMiJ9."));

    }

    @Test(expected = ExpiredJwtException.class)
    public  void sholdBeExpiried (){
        String token ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqYWNlayIsInJvbGUiOiJST0xFX0FETUlOIiwiaWF0IjoxNTcxOTkwMDA0LCJleHAiOjE1NzE5OTAwMDd9.mkvTOmz_udZOztW_i-J-rNTxXampHF87RAJfGlTJ75w6nvRbTxnzSYIiTtTJjdB6nqM1VmTyXSy1GluJjrC17g";
        JwtToken jwtToken = new JwtToken();
        jwtToken.getClaims(token);
    }


}
