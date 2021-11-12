package SKRookie.moamoa.api.controller.studyjoin;

import SKRookie.moamoa.api.dto.StudyDto;
import SKRookie.moamoa.api.dto.joinDto;
import SKRookie.moamoa.api.dto.StudySearchCondition;
import SKRookie.moamoa.api.service.studyjoin.StudyJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/studyJoin")
@RequiredArgsConstructor
public class StudyJoinController {
    
    private final StudyJoinService studyJoinService;


    @GetMapping()
    public ResponseEntity<Page<StudyDto>> search(StudySearchCondition condition, Pageable pageable) {
        return null;
    }

    @PostMapping
    public ResponseEntity<joinDto> newStudy(@RequestBody @Validated joinDto joinDto, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<joinDto> optionalStudyJoinDto = studyJoinService.addStudyJoin(joinDto);

        return  optionalStudyJoinDto.map(studyJoin -> ResponseEntity.status(HttpStatus.CREATED).body(studyJoin)).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
