package pl.codeconcept.e2d.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.codeconcept.e2d.database.entity.enums.ActionType;
import pl.codeconcept.e2d.database.entity.UserRegistration;
import pl.codeconcept.e2d.database.repository.LoginRepo;
import pl.codeconcept.e2d.database.repository.UserRepo;
import pl.codeconcept.e2d.dto.LoginDto;
import pl.codeconcept.e2d.dto.UserDto;
import pl.codeconcept.e2d.service.jwt.JwtToken;
import pl.codeconcept.e2d.service.mapper.LoginMapper;
import pl.codeconcept.e2d.service.mapper.RegistrationMapper;


@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepo;
    private final LoginRepo loginRepo;
    private final JwtToken jwtToken;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto saveUser(UserDto userDto)  {

        String password = passwordEncoder.encode(userDto.getPassword());
        userRepo.save(RegistrationMapper.mapToEntity(userDto,password, ActionType.SIGN_UP));
        return userDto;
    }

    @Transactional
    public String loginUser(LoginDto loginDto)  {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            UserRegistration userRegistration = (UserRegistration) authentication.getPrincipal();
            loginRepo.save(LoginMapper.mapToEntity(loginDto,ActionType.SIGN_IN));
            return jwtToken.generateJwtToken(userRegistration);
        }catch (Exception e){
            e.printStackTrace();
            return "Authentication problem";
        }
    }
}