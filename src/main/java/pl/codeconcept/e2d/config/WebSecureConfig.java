package pl.codeconcept.e2d.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pl.codeconcept.e2d.service.jwt.JwtAuthFilter;
import pl.codeconcept.e2d.service.jwt.JwtToken;
import pl.codeconcept.e2d.database.repository.UserRepo;
import pl.codeconcept.e2d.service.user.UserDetailsServiceImpl;

import java.security.AlgorithmConstraints;
import java.security.MessageDigest;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
@Configuration
public class WebSecureConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtToken jwtToken;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder passwordEncoder () {
        return  new Md5PasswordEncoder();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService)
       .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/signin").permitAll()
                .antMatchers("/signup").hasRole("ADMIN")
                .antMatchers("/logout").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();

    }

    @Bean
    public JwtAuthFilter jwtAuthFilter(AuthenticationManager authenticationManager, JwtToken jwtToken) {
        return new JwtAuthFilter(authenticationManager, jwtToken);
    }



}
