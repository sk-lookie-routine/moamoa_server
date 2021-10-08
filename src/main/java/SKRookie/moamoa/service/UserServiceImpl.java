package SKRookie.moamoa.service;

import SKRookie.moamoa.dto.UserDto;
import SKRookie.moamoa.dto.UserSearchCondition;
import SKRookie.moamoa.entity.User;
import SKRookie.moamoa.repository.UserRepository;
import SKRookie.moamoa.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository _userRepository;

    @Autowired
    ModelMapper _modelMapper;

    @Override
    public Page<UserDto> getUsers(UserSearchCondition condition, Pageable pageable) {
        // TODO Auto-generated method stub
        Page<User> pageCategories = _userRepository.search(condition, pageable);
        List<UserDto> categoryDtos =
                pageCategories.stream().map(category -> {
                    if(!condition.isUseFk()){
                        category.setDomain(null);
                    }
                    return _modelMapper.map(category, UserDto.class);
                }).collect(Collectors.toList());

        return new PageImpl<>(categoryDtos, pageable, pageCategories.getTotalElements());
    }

    @Override
    public Optional<UserDto> addUser(UserDto userDto) {

        User user = _modelMapper.map(userDto, User.class);
        User savedUser = _userRepository.save(user);

        return Optional.ofNullable(_modelMapper.map(savedUser, UserDto.class));
    }
}