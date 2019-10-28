package pl.codeconscept.e2d.cloudauth;


import io.jsonwebtoken.ExpiredJwtException;
import org.junit.Assert;
import org.junit.Test;
import pl.codeconcept.e2d.database.entity.RoleType;
import pl.codeconcept.e2d.database.entity.User;
import pl.codeconcept.e2d.service.jwt.JwtToken;

public class JwtTokenTest {

    User prinicple = new User("jacek","1234", RoleType.ROLE_ADMIN);

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
