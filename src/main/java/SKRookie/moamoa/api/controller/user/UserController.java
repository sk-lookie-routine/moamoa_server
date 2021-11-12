package SKRookie.moamoa.api.controller.user;

import SKRookie.moamoa.api.entity.user.User;
import SKRookie.moamoa.api.service.user.UserService;
import SKRookie.moamoa.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ApiResponse getUsers() {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();

        String username = loggedInUser.getName();

        User user = userService.getUser(username);

        return ApiResponse.success("user", user);
    }
}
