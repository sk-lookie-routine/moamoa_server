package SKRookie.moamoa.api.service.join;

import SKRookie.moamoa.api.dto.*;
import SKRookie.moamoa.api.entity.join.Join;
import SKRookie.moamoa.api.entity.study.Study;
import SKRookie.moamoa.api.entity.user.User;
import SKRookie.moamoa.api.repository.join.JoinRepositoryCustom;
import SKRookie.moamoa.api.repository.join.JoinRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final JoinRepository joinRepository;

    private final ModelMapper modelMapper;

    public Optional<JoinDto> addJoin(JoinDto joinDto) {

        Join savedStudyJoin = joinRepository.save(modelMapper.map(joinDto, Join.class));

        return Optional.of(modelMapper.map(savedStudyJoin, JoinDto.class));
    }

    public Page<JoinDto> getJoin(JoinSearchCondition condition, Pageable pageable) {
        Page<Join> search = joinRepository.search(condition, pageable);

        List<JoinDto> joinDtos = search.stream().map(join -> modelMapper.map(join, JoinDto.class)).collect(Collectors.toList());

        return new PageImpl<>(joinDtos, pageable, search.getTotalElements());
    }

    public Optional<JoinDto> updateJoin(JoinDto joinDto) {

        Optional<Join> byId = joinRepository.findById(joinDto.getJoinSeq());

        Join join = byId.get();

        join.setJoinType(joinDto.getJoinType());
        joinRepository.saveAndFlush(join);

        return Optional.of(modelMapper.map(join, JoinDto.class));
    }

    public void deleteJoinByUserSeq(UserDto userDto) {

        List<Join> allByJoinUser = joinRepository.findAllByJoinUser(modelMapper.map(userDto, User.class));

        if (!allByJoinUser.isEmpty()) {
            joinRepository.deleteAllByJoinUser(modelMapper.map(userDto, User.class));
        }
    }

    public void deleteJoinByStudySeq(StudyDto studyDto) {

        List<Join> allByJoinStudy = joinRepository.findAllByJoinStudy(modelMapper.map(studyDto, Study.class));

        if (!allByJoinStudy.isEmpty()) {
            joinRepository.deleteAllByJoinStudy(modelMapper.map(studyDto, Study.class));
        }
    }
}

