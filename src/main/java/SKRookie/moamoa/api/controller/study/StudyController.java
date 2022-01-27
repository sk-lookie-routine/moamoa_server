package SKRookie.moamoa.api.controller.study;

import SKRookie.moamoa.api.dto.*;
import SKRookie.moamoa.api.entity.study.Study;
import SKRookie.moamoa.api.enums.JoinType;
import SKRookie.moamoa.api.enums.MateType;
import SKRookie.moamoa.api.enums.PostType;
import SKRookie.moamoa.api.enums.StudyType;
import SKRookie.moamoa.api.service.join.JoinService;
import SKRookie.moamoa.api.service.mate.MateService;
import SKRookie.moamoa.api.service.post.PostService;
import SKRookie.moamoa.api.service.study.StudyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/study")
@RequiredArgsConstructor
public class StudyController {

    private final StudyService studyService;

    private final PostService postService;

    private final JoinService joinService;

    private final MateService mateService;

    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<Page<StudyDto>> search(StudySearchCondition condition, Pageable pageable) {

        // get conditionally
        Page<StudyDto> studyDtoPage = studyService.search(condition, pageable);

        if (studyDtoPage.hasContent()) {
            return ResponseEntity.status(HttpStatus.OK).body(studyDtoPage);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudyDto> getStudy(@PathVariable Long id) {

        // get one
        Optional<StudyDto> studyDto = studyService.getStudy(id);

        return studyDto.map(dto -> ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<StudyDto> newStudy(@RequestBody @Validated PostDto postDto, Errors errors, Pageable pageable){
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        // add one

        // post -> study & save
        PostDto byIdPostDto = postService.findOne(postDto).get();
        byIdPostDto.setPostType(PostType.COMPLETE);
        postService.updatePost(byIdPostDto);

        StudyDto map = modelMapper.map(byIdPostDto, StudyDto.class);
        map.setStudyType(StudyType.PROGRESS);
        Optional<StudyDto> savedStudyDto = studyService.addStudy(map);
        StudyDto studyDto = savedStudyDto.get();

        // join -> mate & save
        joinService.updateJoinByType(postDto, studyDto);

        return ResponseEntity.status(HttpStatus.OK).body(studyDto);
    }

    @PutMapping
    public ResponseEntity<StudyDto> updateStudy(@RequestBody @Validated StudyDto studyDto, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        // update one
        Optional<StudyDto> optionalStudyDto = studyService.updateStudy(studyDto);

        return  optionalStudyDto.map(study -> ResponseEntity.status(HttpStatus.OK).body(study)).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
