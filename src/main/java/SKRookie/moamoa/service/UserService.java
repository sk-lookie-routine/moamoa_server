package SKRookie.moamoa.service;

import SKRookie.moamoa.dto.UserDto;
import SKRookie.moamoa.dto.UserSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {

    Page<UserDto> getUsers(UserSearchCondition condition, Pageable pageable);

    Optional<UserDto> addUser(UserDto userDto);
}
