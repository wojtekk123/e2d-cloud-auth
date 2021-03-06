package pl.codeconcept.e2d.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.codeconcept.e2d.database.entity.UserRegistration;
import pl.codeconcept.e2d.database.entity.enums.ActionType;
import pl.codeconcept.e2d.database.repository.LoginRepo;
import pl.codeconcept.e2d.database.repository.UserRepo;
import pl.codeconcept.e2d.dto.LoginDto;
import pl.codeconcept.e2d.dto.BackDto;
import pl.codeconcept.e2d.dto.Response;
import pl.codeconcept.e2d.dto.UserDto;
import pl.codeconcept.e2d.service.jwt.JwtToken;
import pl.codeconcept.e2d.service.mapper.LoginMapper;
import pl.codeconcept.e2d.service.mapper.RegistrationMapper;

import java.util.HashSet;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    private final LoginRepo loginRepo;
    private final JwtToken jwtToken;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseEntity<BackDto> saveUser(UserDto userDto) {
        try {
            String password = passwordEncoder.encode(userDto.getPassword());
            UserRegistration save = userRepo.save(RegistrationMapper.mapToEntity(userDto, password, ActionType.SIGN_UP));
            return new ResponseEntity<>(LoginMapper.mapToBackDto(userDto, save.getId()), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public ResponseEntity<Response> loginUser(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            UserRegistration userRegistration = (UserRegistration) authentication.getPrincipal();
            loginRepo.save(LoginMapper.mapToEntity(loginDto, ActionType.SIGN_IN));
            return new ResponseEntity <> (Response.builder().message("Successful").token(jwtToken.generateJwtToken(userRegistration)).build(),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Authentication problem");
            return new ResponseEntity<>(Response.builder().message("Access Denied").token(null).build(), HttpStatus.BAD_REQUEST);
        }
    }
}
