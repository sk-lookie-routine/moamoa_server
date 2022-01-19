package SKRookie.moamoa.api.controller.auth;

import SKRookie.moamoa.api.dto.UserDto;
import SKRookie.moamoa.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;


    // login
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto) {
        Optional<UserDto> rejectedUser = userService.getRejectedUser(userDto.getEmail());
        // if rejected already
        if (rejectedUser.isPresent()) {
            ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE);
        }
        // if not rejected
        Optional<UserDto> optionalUserDto = userService.loginUser(userDto);

        return optionalUserDto.map(user -> ResponseEntity.status(HttpStatus.OK).body(user)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping ("/reject")
    public ResponseEntity<UserDto> reject(@RequestBody UserDto userDto) {
        // user find & update
        Optional<UserDto> rejectedUser = userService.rejectUser(userDto);

        return rejectedUser.map(user -> ResponseEntity.status(HttpStatus.OK).body(user)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}
