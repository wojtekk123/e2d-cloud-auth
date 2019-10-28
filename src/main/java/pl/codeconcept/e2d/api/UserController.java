package pl.codeconcept.e2d.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.codeconcept.e2d.database.entity.ActionType;
import pl.codeconcept.e2d.database.entity.RoleType;
import pl.codeconcept.e2d.database.entity.UserActivity;
import pl.codeconcept.e2d.dto.LoginDto;
import pl.codeconcept.e2d.database.entity.User;
import pl.codeconcept.e2d.dto.RegistrationDto;
import pl.codeconcept.e2d.service.jwt.JwtToken;
import org.springframework.web.bind.annotation.*;
import pl.codeconcept.e2d.service.user.UserActivityService;
import pl.codeconcept.e2d.service.user.UserSrvice;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtToken jwtToken;
    private final UserActivityService userActivityService;
    private final UserSrvice userSrvice;

    @PostMapping("/signup")
    public ResponseEntity<String> singUp(@RequestBody RegistrationDto registrationDto) {

        RoleType roleType = userSrvice.getRoleType(registrationDto);

        if (userSrvice.existByUsername(registrationDto.getUsername())) {
            return new ResponseEntity<String>("User is already exist", HttpStatus.BAD_REQUEST);
        } else if (roleType == null) {
            return new ResponseEntity<String>("You selected wrong role", HttpStatus.BAD_REQUEST);
        } else {
            userSrvice.save(new User(registrationDto.getUsername(), passwordEncoder.encode(registrationDto.getPassword()), roleType));
            userActivityService.saveActivity(registrationDto.getUsername(), ActionType.SIGN_UP);
            return ResponseEntity.ok().body("User registered successfully");
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody LoginDto loginDto) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

            String jwt = jwtToken.generateJwtToken((User) authentication.getPrincipal());
            userActivityService.saveActivity(loginDto.getUsername(), ActionType.SIGN_IN);

            return ResponseEntity.ok(jwt);
        } catch (InternalAuthenticationServiceException e) {
            return new ResponseEntity<String>("Illegal user", HttpStatus.BAD_REQUEST);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<String>("Illegal password", HttpStatus.BAD_REQUEST);
        }
    }
}
