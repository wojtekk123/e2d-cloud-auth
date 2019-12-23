package pl.codeconscept.e2d.cloudauth;

import org.junit.Assert;
import org.junit.Test;

import pl.codeconcept.e2d.database.entity.UserRegistration;
import pl.codeconcept.e2d.database.entity.enums.RoleType;
import pl.codeconcept.e2d.service.jwt.JwtToken;


public class JwtTokenTest {

    private UserRegistration principle = new UserRegistration ("marek","1234", RoleType.ROLE_ADMIN);
    private JwtToken jwtToken = new JwtToken();

    @Test
    public void schouldCheckToken () {
        String token = jwtToken.generateJwtToken(principle);
        Assert.assertNotNull(token);
    }

    @Test
    public void shouldStartWith (){

        String jwt=  jwtToken.generateJwtToken(principle);
        Assert.assertTrue(jwt.startsWith("eyJhbGciOiJIUzUxMiJ9."));

    }
}