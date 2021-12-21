package SKRookie.moamoa.api.controller.user;

import SKRookie.moamoa.api.dto.UserDto;
import SKRookie.moamoa.api.entity.user.UserRefreshToken;
import SKRookie.moamoa.api.repository.user.UserRefreshTokenRepository;
import SKRookie.moamoa.api.repository.user.UserRepository;
import SKRookie.moamoa.api.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRefreshTokenRepository userRefreshTokenRepository;

    private final UserRepository userRepository;

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<UserDto>  getLoginUser(@RequestParam String token) {

        UserRefreshToken byRefreshToken = userRefreshTokenRepository.findByRefreshToken(token);

        Optional<UserDto> userDto = userService.getUser(byRefreshToken.getUserId());

        return userDto.map(user -> ResponseEntity.status(HttpStatus.OK).body(user)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody @Validated UserDto userDto, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<UserDto> updatedUser = userService.updateUser(userDto);

        return  updatedUser.map(user -> ResponseEntity.status(HttpStatus.OK).body(user)).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
