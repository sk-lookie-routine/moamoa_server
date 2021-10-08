//Controller
package SKRookie.moamoa.controller;

import SKRookie.moamoa.dto.UserDto;
import SKRookie.moamoa.dto.UserSearchCondition;
import SKRookie.moamoa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService _userService;

    @GetMapping("/search")
    public ResponseEntity<Page<UserDto>> search(UserSearchCondition condition, Pageable pageable) {

        Page<UserDto> userDtoPage = _userService.getUsers(condition, pageable);

        if (userDtoPage.hasContent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userDtoPage);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserDto> newCategory(@RequestBody @Validated UserDto userDto, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<UserDto> optionalUserDto = _userService.addUser(userDto);

        return  optionalUserDto.map(category -> ResponseEntity.status(HttpStatus.CREATED).body(category)).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}

