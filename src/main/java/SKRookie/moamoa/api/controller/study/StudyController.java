package SKRookie.moamoa.api.controller.study;

import SKRookie.moamoa.api.dto.StudyDto;
import SKRookie.moamoa.api.enums.StudyType;
import SKRookie.moamoa.api.service.study.StudyService;
import SKRookie.moamoa.common.ApiResponse;
import lombok.RequiredArgsConstructor;
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

    @GetMapping()
    public ApiResponse getAll() {
        return ApiResponse.success("hello", null);
    }
    @PostMapping
    public ResponseEntity<StudyDto> newStudy(@RequestBody @Validated StudyDto studyDto, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        studyDto.setCreatedAt(LocalDateTime.now());
        studyDto.setModifiedAt(LocalDateTime.now());
        studyDto.setStudyType(StudyType.READY);

        Optional<StudyDto> optionalStudyDto = studyService.addStudy(studyDto);

        return  optionalStudyDto.map(study -> ResponseEntity.status(HttpStatus.CREATED).body(study)).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
