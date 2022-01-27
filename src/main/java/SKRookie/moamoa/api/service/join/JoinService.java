package SKRookie.moamoa.api.service.join;

import SKRookie.moamoa.api.dto.*;
import SKRookie.moamoa.api.entity.join.Join;
import SKRookie.moamoa.api.entity.mate.Mate;
import SKRookie.moamoa.api.entity.study.Study;
import SKRookie.moamoa.api.entity.user.User;
import SKRookie.moamoa.api.enums.JoinType;
import SKRookie.moamoa.api.enums.MateType;
import SKRookie.moamoa.api.repository.join.JoinRepositoryCustom;
import SKRookie.moamoa.api.repository.join.JoinRepository;
import SKRookie.moamoa.api.repository.mate.MateRepository;
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
public class JoinService {
    private final MateRepository mateRepository;

    private final JoinRepository joinRepository;

    private final ModelMapper modelMapper;

    public Optional<JoinDto> addJoin(JoinDto joinDto) {
        Join join = modelMapper.map(joinDto, Join.class);

        join.setCreatedAt(LocalDateTime.now());

        Join savedStudyJoin = joinRepository.save(join);

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

    public void deleteJoin(Long join_id) {
        joinRepository.deleteById(join_id);
    }

    public void deleteJoinsByPostSeq(Long post_id)  {

        List<Join> allByJoinPost_postSeq = joinRepository.findAllByJoinPost_PostSeq(post_id);

        allByJoinPost_postSeq.forEach((Join) -> {
            deleteJoin(Join.getJoinSeq());
        });
    }

    public void updateJoinByType(PostDto postDto, StudyDto studyDto) {
        List<Join> allByJoinPost_postSeq = joinRepository.findAllByJoinPost_PostSeq(postDto.getPostSeq());

        allByJoinPost_postSeq.forEach(join -> {
            // join 이 approved 인 경우에만 생성
            if (join.getJoinType().equals(JoinType.WAIT)) {
                join.setJoinType(JoinType.REFUSED);
            }
            if (join.getJoinType().equals(JoinType.APPROVED)) {
                MateDto tmpMateDto = new MateDto();
                tmpMateDto.setStudySeq(studyDto.getStudySeq());
                tmpMateDto.setMateType(MateType.PROGRESS);
                tmpMateDto.setUserSeq(join.getJoinUser().getUserSeq());
                mateRepository.save(modelMapper.map(tmpMateDto, Mate.class));
            }
        });
        joinRepository.flush();
        mateRepository.flush();
    }
}

