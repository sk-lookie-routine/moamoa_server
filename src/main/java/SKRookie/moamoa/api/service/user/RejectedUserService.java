package SKRookie.moamoa.api.service.user;


import SKRookie.moamoa.api.dto.RejectedUserDto;
import SKRookie.moamoa.api.dto.ReplyDto;
import SKRookie.moamoa.api.dto.UserDto;
import SKRookie.moamoa.api.dto.UserSearchCondition;
import SKRookie.moamoa.api.entity.reply.Reply;
import SKRookie.moamoa.api.entity.user.RejectedUser;
import SKRookie.moamoa.api.entity.user.User;
import SKRookie.moamoa.api.repository.user.RejectedUserRepository;
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
public class RejectedUserService {
    private final RejectedUserRepository rejectedUserRepository;

    private final ModelMapper modelMapper;

    public Optional<RejectedUserDto> getRejectedUserByEmail(String email) {
        RejectedUser byEmail = rejectedUserRepository.findByEmail(email);

        return Optional.of(modelMapper.map(byEmail, RejectedUserDto.class));
    }

    public Optional<RejectedUserDto> addRejectedUser(RejectedUserDto rejectedUserDto) {
        RejectedUser save = rejectedUserRepository.save(modelMapper.map(rejectedUserDto, RejectedUser.class));

        return Optional.of(modelMapper.map(save, RejectedUserDto.class));
    }
}
