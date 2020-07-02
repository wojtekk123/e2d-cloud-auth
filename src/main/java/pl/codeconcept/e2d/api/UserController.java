package pl.codeconcept.e2d.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.codeconcept.e2d.dto.LoginDto;
import pl.codeconcept.e2d.dto.BackDto;
import pl.codeconcept.e2d.dto.Response;
import pl.codeconcept.e2d.dto.UserDto;
import pl.codeconcept.e2d.service.user.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<BackDto> singUp(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @PostMapping("/signin")
    public ResponseEntity<Response> signIn(@RequestBody LoginDto loginDto) {
        return userService.loginUser(loginDto);
    }
}
