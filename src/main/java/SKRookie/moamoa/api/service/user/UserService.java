package SKRookie.moamoa.api.service.user;

import SKRookie.moamoa.api.dto.StudyDto;
import SKRookie.moamoa.api.dto.StudySearchCondition;
import SKRookie.moamoa.api.dto.UserSearchCondition;
import SKRookie.moamoa.api.entity.study.Study;
import SKRookie.moamoa.api.entity.user.User;
import SKRookie.moamoa.api.dto.UserDto;
import SKRookie.moamoa.api.enums.UserType;
import SKRookie.moamoa.api.repository.user.UserCustomRepository;
import SKRookie.moamoa.api.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final UserCustomRepository userCustomRepository;

    private final ModelMapper modelMapper;

    public Page<UserDto> getUser(UserSearchCondition condition, Pageable pageable) {
        Page<User> search = userCustomRepository.search(condition, pageable);

        List<UserDto> userDtos = search.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

        return new PageImpl<>(userDtos, pageable, search.getTotalElements());
    }

    public Optional<UserDto> findUser(Long user_seq) {
        Optional<User> byId = userRepository.findById(user_seq);

        return Optional.of(modelMapper.map(byId.get(), UserDto.class));
    }

    public Optional<UserDto> updateUser(UserDto userDto) {

        Optional<User> optionalUser = userRepository.findById(userDto.getUserSeq());
        User user = optionalUser.get();

        if(optionalUser.isPresent()) {
            user.setUsername(userDto.getUsername());
            user.setUserInfo(userDto.getUserInfo());
            user.setImage(userDto.getImage());
        }

        userRepository.save(user);

        return Optional.of(modelMapper.map(user, UserDto.class));
    }

    public Optional<UserDto> loginUser(UserDto userDto) {

        User byUserId = userRepository.findByUserId(userDto.getUserId());

        if(byUserId == null) {
            userDto.setCreatedAt(LocalDateTime.now());
            byUserId = userRepository.save(modelMapper.map(userDto, User.class));
        }

        return Optional.of(modelMapper.map(byUserId, UserDto.class));
    }

    public Optional<UserDto> getRejectedUser(String email) {
        User byUserSeqAndUserType = userRepository.findByEmailAndUserType(email, UserType.REJECT);

        return Optional.ofNullable(byUserSeqAndUserType == null ? null : modelMapper.map(byUserSeqAndUserType, UserDto.class));
    }

    public Optional<UserDto> rejectUser(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(userDto.getUserSeq());
        User user = optionalUser.get();

        if(optionalUser.isPresent()) {
            user.setUserType(UserType.REJECT);
        }

        userRepository.save(user);

        return Optional.of(modelMapper.map(user, UserDto.class));
    }
}
