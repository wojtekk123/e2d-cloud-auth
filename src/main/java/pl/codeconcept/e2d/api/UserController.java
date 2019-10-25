package pl.codeconcept.e2d.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import pl.codeconcept.e2d.database.entity.UserActivity;
import pl.codeconcept.e2d.dto.Dto;
import pl.codeconcept.e2d.database.entity.Users;
import pl.codeconcept.e2d.service.jwt.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.codeconcept.e2d.service.user.UserActivityService;
import pl.codeconcept.e2d.service.user.UserSrvice;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;

@RestController
public class UserController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserSrvice userSrvice;
    @Autowired
        private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtToken jwtToken;
    @Autowired
    UserActivityService userActivityService;

    Date date = new Date();

    @PostMapping("/signup")
    public ResponseEntity<String> singUp(@RequestBody Users users) {
        if (userSrvice.existByUsername(users.getUsername())) {
            return new ResponseEntity<String>("User is already exist", HttpStatus.BAD_REQUEST);
        }

        userSrvice.save(new Users(users.getUsername(), passwordEncoder.encode(users.getPassword()), users.getRole()));

        userActivityService.saveActivity(users.getUsername(),"SIGN_UP");

        return ResponseEntity.ok().body("User registered seccessfully");
    }


    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody Dto dto) {


        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtToken.generateJwtToken((Users)authentication.getPrincipal());

            userActivityService.saveActivity(dto.getUsername(),"SIGN_IN");


            return ResponseEntity.ok(jwt);
        } catch (InternalAuthenticationServiceException e) {
            return new ResponseEntity<String>("Illegal user", HttpStatus.BAD_REQUEST);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<String>("Illegal password", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/logout")
    public String Logout (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null)
            new SecurityContextLogoutHandler().logout(httpServletRequest,httpServletResponse,authentication);
        return "ok";
    }




}
