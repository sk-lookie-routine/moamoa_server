package SKRookie.moamoa.api.controller.study;

import SKRookie.moamoa.api.dto.StudyDto;
import SKRookie.moamoa.api.dto.StudySearchCondition;
import SKRookie.moamoa.api.enums.StudyType;
import SKRookie.moamoa.api.service.join.JoinService;
import SKRookie.moamoa.api.service.study.StudyService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping
    public ResponseEntity<Page<StudyDto>> search(StudySearchCondition condition, Pageable pageable) {

        // mypage 에서 요청했을 때와 전체 목록에서 요청했을 때 서로 다른 study를 보내줘야 한다.
        Page<StudyDto> studyDtoPage = condition.getIsMyPage() != null ? studyService.getMyStudy(condition, pageable) : studyService.getStudy(condition, pageable);

        if (studyDtoPage.hasContent()) {
            return ResponseEntity.status(HttpStatus.OK).body(studyDtoPage);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<StudyDto> newStudy(@RequestBody @Validated StudyDto studyDto, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        studyDto.setCreatedAt(LocalDateTime.now());

        Optional<StudyDto> optionalStudyDto = studyService.addStudy(studyDto);

        return  optionalStudyDto.map(study -> ResponseEntity.status(HttpStatus.CREATED).body(study)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping
    public ResponseEntity<StudyDto> updateStudy(@RequestBody @Validated StudyDto studyDto, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        studyDto.setModifiedAt(LocalDateTime.now());

        Optional<StudyDto> optionalStudyDto = studyService.addStudy(studyDto);

        return  optionalStudyDto.map(study -> ResponseEntity.status(HttpStatus.CREATED).body(study)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // 연관관계 때문에 DB에서 진짜 삭제하는건 어려울 것 같음 그냥 STATUS 를 바꿈
//    @DeleteMapping
//    public ResponseEntity<StudyDto> deleteStudy(@RequestBody @Validated StudyDto studyDto, Errors errors){
//        if(errors.hasErrors()) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        Optional<StudyDto> optionalStudyDto = studyService.deleteStudy(studyDto);
//
//        return  optionalStudyDto.map(study -> ResponseEntity.status(HttpStatus.OK).body(study)).orElseGet(() -> ResponseEntity.badRequest().build());
//    }
}
