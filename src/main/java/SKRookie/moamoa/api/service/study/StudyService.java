package SKRookie.moamoa.api.service.study;

import SKRookie.moamoa.api.dto.*;
import SKRookie.moamoa.api.entity.post.Post;
import SKRookie.moamoa.api.entity.reply.Reply;
import SKRookie.moamoa.api.entity.study.Study;
import SKRookie.moamoa.api.entity.user.User;
import SKRookie.moamoa.api.repository.join.JoinRepository;
import SKRookie.moamoa.api.repository.study.StudyRepositoryCustom;
import SKRookie.moamoa.api.repository.study.StudyRepository;
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
public class StudyService {
    private final StudyRepository studyRepository;

    private final ModelMapper modelMapper;


    public Optional<StudyDto> addStudy(StudyDto studyDto) {
        Study map = modelMapper.map(studyDto, Study.class);
        //수정 날짜 변경
        map.setModifiedAt(LocalDateTime.now());

        Study savedStudy = studyRepository.save(map);

        return Optional.of(modelMapper.map(savedStudy, StudyDto.class));
    }

    public Page<StudyDto> search(StudySearchCondition condition, Pageable pageable) {
        PageImpl<Study> search = studyRepository.search(condition, pageable);

        List<StudyDto> studyDtos = search.stream().map(study -> modelMapper.map(study, StudyDto.class)).collect(Collectors.toList());

        return new PageImpl<>(studyDtos, pageable, search.getTotalElements());
    }

    public Optional<StudyDto> getStudy(Long study_id) {
        Study byIdStudy = studyRepository.findById(study_id).get();

        return Optional.of(modelMapper.map(byIdStudy, StudyDto.class));
    }

    public Optional<StudyDto> updateStudy(StudyDto studyDto) {

        Study study = studyRepository.findById(studyDto.getStudySeq()).get();

        study.setModifiedAt(LocalDateTime.now());
        study.setCreatedAt(studyDto.getCreatedAt());

        study.setLinkStudy(studyDto.getLinkStudy());
        study.setLinkChat(studyDto.getLinkChat());
        study.setLinkNotion(studyDto.getLinkNotion());

        study.setEndDate(studyDto.getEndDate());
        study.setStartDate(studyDto.getStartDate());
        study.setGoal(studyDto.getGoal());
        study.setHashTags(studyDto.getHashTags());
        study.setHow(studyDto.getHow());
        study.setInfo(studyDto.getInfo());
        study.setTitle(studyDto.getTitle());
        study.setStudyType(studyDto.getStudyType());

        studyRepository.saveAndFlush(study);

        return Optional.of(modelMapper.map(study, StudyDto.class));
    }
}
