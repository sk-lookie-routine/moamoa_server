package SKRookie.moamoa.api.service.join;

import SKRookie.moamoa.api.dto.StudyDto;
import SKRookie.moamoa.api.dto.StudySearchCondition;
import SKRookie.moamoa.api.dto.JoinDto;
import SKRookie.moamoa.api.entity.join.Join;
import SKRookie.moamoa.api.entity.study.Study;
import SKRookie.moamoa.api.entity.user.User;
import SKRookie.moamoa.api.repository.join.JoinRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final JoinRepository joinRepository;

    private final ModelMapper modelMapper;

    public Optional<JoinDto> addStudyJoin(JoinDto joinDto) {

        Join savedStudyJoin = joinRepository.save(modelMapper.map(joinDto, Join.class));

        return Optional.of(modelMapper.map(savedStudyJoin, JoinDto.class));
    }

    public Page<StudyDto> getStudyJoin(StudySearchCondition condition, Pageable pageable) {
//        Page<Study> search = studyCustomRepository.search(condition, pageable);
//
//        List<StudyDto> studyDtos = search.stream().map(study -> modelMapper.map(study, StudyDto.class)).collect(Collectors.toList());
//
//        return new PageImpl<>(studyDtos, pageable, search.getTotalElements());
        return null;
    }

    public Optional<JoinDto> deleteStudyJoin(JoinDto joinDto) {

        Join savedStudyJoin = joinRepository.save(modelMapper.map(joinDto, Join.class));

        return Optional.of(modelMapper.map(savedStudyJoin, JoinDto.class));
    }
}

