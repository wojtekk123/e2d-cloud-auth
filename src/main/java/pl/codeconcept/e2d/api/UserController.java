package pl.codeconcept.e2d.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.codeconcept.e2d.dto.LoginDto;
import pl.codeconcept.e2d.dto.UserDto;
import org.springframework.web.bind.annotation.*;
import pl.codeconcept.e2d.service.user.UserService;

import javax.validation.ConstraintViolationException;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> singUp(@RequestBody UserDto userDto)  {
        try {
            return new ResponseEntity<>(userService.saveUser(userDto), HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(userService.loginUser(loginDto), HttpStatus.OK);
    }
}
