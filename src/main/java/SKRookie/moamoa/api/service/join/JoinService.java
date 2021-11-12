package SKRookie.moamoa.api.service.studyjoin;

import SKRookie.moamoa.api.dto.StudyDto;
import SKRookie.moamoa.api.dto.StudySearchCondition;
import SKRookie.moamoa.api.dto.joinDto;
import SKRookie.moamoa.api.entity.join.Join;
import SKRookie.moamoa.api.repository.join.StudyJoinRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudyJoinService {
    private final StudyJoinRepository studyJoinRepository;

    private final ModelMapper modelMapper;

    public Optional<joinDto> addStudyJoin(joinDto joinDto) {

        Join map = modelMapper.map(joinDto, Join.class);

        Join savedStudyJoin = studyJoinRepository.save(map);

        return Optional.of(modelMapper.map(savedStudyJoin, joinDto.class));
    }

    public Page<StudyDto> getStudyJoin(StudySearchCondition condition, Pageable pageable) {
//        Page<Study> search = studyCustomRepository.search(condition, pageable);
//
//        List<StudyDto> studyDtos = search.stream().map(study -> modelMapper.map(study, StudyDto.class)).collect(Collectors.toList());
//
//        return new PageImpl<>(studyDtos, pageable, search.getTotalElements());
        return null;
    }
}

