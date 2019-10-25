package pl.codeconscept.e2d.cloudauth;


import io.jsonwebtoken.ExpiredJwtException;
import org.junit.Assert;
import org.junit.Test;
import pl.codeconcept.e2d.config.Md5PasswordEncoder;
import pl.codeconcept.e2d.database.entity.Users;
import pl.codeconcept.e2d.service.jwt.JwtToken;

public class Md5PasswordEncodeTest {


    @Test
    public void scholudEncode() {

        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();

        String pass = md5PasswordEncoder.encode("1234");
        Assert.assertEquals(pass,"81dc9bdb52d04dc20036dbd8313ed055");

        String pass2 = md5PasswordEncoder.encode("test123");
        Assert.assertEquals(pass2,"cc03e747a6afbbcbf8be7668acfebee5");

    }
}
