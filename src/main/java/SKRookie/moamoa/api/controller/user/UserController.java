package SKRookie.moamoa.api.controller.user;

import SKRookie.moamoa.api.entity.user.User;
import SKRookie.moamoa.api.service.user.UserService;
import SKRookie.moamoa.common.ApiResponse;
import lombok.RequiredArgsConstructor;
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
    public ApiResponse getUser() {
        Object principal1 = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(principal.getUsername());

        return ApiResponse.success("user", user);
    }
}
