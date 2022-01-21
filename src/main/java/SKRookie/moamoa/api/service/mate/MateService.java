package SKRookie.moamoa.api.service.mate;

import SKRookie.moamoa.api.dto.*;
import SKRookie.moamoa.api.entity.join.Join;
import SKRookie.moamoa.api.entity.mate.Mate;
import SKRookie.moamoa.api.entity.study.Study;
import SKRookie.moamoa.api.entity.user.User;
import SKRookie.moamoa.api.repository.join.JoinRepositoryCustom;
import SKRookie.moamoa.api.repository.join.JoinRepository;
import SKRookie.moamoa.api.repository.mate.MateRepository;
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
public class MateService {
    private final MateRepository mateRepository;

    private final ModelMapper modelMapper;

    public Optional<MateDto> addMate(MateDto mateDto) {

        Mate savedStudyJoin = mateRepository.save(modelMapper.map(mateDto, Mate.class));

        return Optional.of(modelMapper.map(savedStudyJoin, MateDto.class));
    }

    public Page<MateDto> search(MateSearchCondition condition, Pageable pageable) {
        Page<Mate> search = mateRepository.search(condition, pageable);

        List<MateDto> mateDtos = search.stream().map(join -> modelMapper.map(join, MateDto.class)).collect(Collectors.toList());

        return new PageImpl<>(mateDtos, pageable, search.getTotalElements());
    }

    public Optional<MateDto> updateMate(MateDto mateDto) {

        Optional<Mate> byId = mateRepository.findById(mateDto.getMateSeq());

        Mate mate = byId.get();

        mate.setMateType(mateDto.getMateType());
        mateRepository.saveAndFlush(mate);

        return Optional.of(modelMapper.map(mate, MateDto.class));
    }
}

