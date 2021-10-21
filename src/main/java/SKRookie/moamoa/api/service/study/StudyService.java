package SKRookie.moamoa.api.service.study;

import SKRookie.moamoa.api.dto.StudyDto;
import SKRookie.moamoa.api.entity.study.Study;
import SKRookie.moamoa.api.entity.user.User;
import SKRookie.moamoa.api.repository.study.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudyService {
    private final StudyRepository studyRepository;

    private final ModelMapper modelMapper;

    public Optional<StudyDto> addStudy(StudyDto studyDto) {
        Study savedStudy = studyRepository.save(modelMapper.map(studyDto, Study.class));

        return Optional.of(modelMapper.map(savedStudy, StudyDto.class));
    }
}
