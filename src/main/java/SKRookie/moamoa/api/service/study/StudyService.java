package SKRookie.moamoa.api.service.study;

import SKRookie.moamoa.api.dto.StudyDto;
import SKRookie.moamoa.api.dto.StudySearchCondition;
import SKRookie.moamoa.api.dto.UserDto;
import SKRookie.moamoa.api.entity.join.Join;
import SKRookie.moamoa.api.entity.study.Study;
import SKRookie.moamoa.api.entity.user.User;
import SKRookie.moamoa.api.repository.study.StudyCustomRepository;
import SKRookie.moamoa.api.repository.study.StudyRepository;
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
public class StudyService {
    private final StudyRepository studyRepository;

    private final StudyCustomRepository studyCustomRepository;

    private final ModelMapper modelMapper;

    public Optional<StudyDto> addStudy(StudyDto studyDto) {
        Study map = modelMapper.map(studyDto, Study.class);
        //수정 날짜 변경
        map.setModifiedAt(LocalDateTime.now());

        Study savedStudy = studyRepository.save(map);

        return Optional.of(modelMapper.map(savedStudy, StudyDto.class));
    }

    public Page<StudyDto> getStudy(StudySearchCondition condition, Pageable pageable) {
        Page<Study> search = studyCustomRepository.search(condition, pageable);

        List<StudyDto> studyDtos = search.stream().map(study -> modelMapper.map(study, StudyDto.class)).collect(Collectors.toList());

        return new PageImpl<>(studyDtos, pageable, search.getTotalElements());
    }

    public void deleteStudyByUserSeq(UserDto userDto) {

        List<Study> allByStudyUser = studyRepository.findAllByStudyUser(modelMapper.map(userDto, User.class));

        if (!allByStudyUser.isEmpty()) {
            studyRepository.deleteAllByStudyUser(modelMapper.map(userDto, User.class));
        }
    }
}
