package pl.codeconcept.e2d.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.codeconcept.e2d.service.jwt.JwtAuthFilter;
import pl.codeconcept.e2d.service.jwt.JwtToken;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
@Configuration
public class WebSecureConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtToken jwtToken;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/signin").permitAll()
                .antMatchers("/signup").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .addFilter(jwtAuthFilter(authenticationManagerBean(), jwtToken))
                .csrf().disable();
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter(AuthenticationManager authenticationManager, JwtToken jwtToken) {
        return new JwtAuthFilter(authenticationManager, jwtToken);
    }
}
