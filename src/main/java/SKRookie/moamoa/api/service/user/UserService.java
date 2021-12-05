package SKRookie.moamoa.api.service.user;

import SKRookie.moamoa.api.entity.user.User;
import SKRookie.moamoa.api.dto.UserDto;
import SKRookie.moamoa.api.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public Optional<UserDto> getUser(String user_id) {

        User byUserId = userRepository.findByUserId(user_id);
        return Optional.of(modelMapper.map(byUserId, UserDto.class));
    }

    public Optional<UserDto> addUser(UserDto userDto) {

        Optional<User> optionalUser = userRepository.findById(userDto.getUserSeq());
        User user = optionalUser.get();

        if(optionalUser.isPresent()) {
            user.setUsername(userDto.getUsername());
            user.setUserInfo(userDto.getUserInfo());
            user.setImage(userDto.getImage());
            user.setModifiedAt(LocalDateTime.now());
        }

        userRepository.save(user);

        return Optional.of(modelMapper.map(user, UserDto.class));
    }
}
