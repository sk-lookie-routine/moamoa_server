package SKRookie.moamoa.api.controller.join;

import SKRookie.moamoa.api.dto.JoinSearchCondition;
import SKRookie.moamoa.api.dto.StudyDto;
import SKRookie.moamoa.api.dto.JoinDto;
import SKRookie.moamoa.api.dto.StudySearchCondition;
import SKRookie.moamoa.api.service.join.JoinService;
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
@RequestMapping("/api/join")
@RequiredArgsConstructor
public class JoinController {
    
    private final JoinService joinService;

    @GetMapping
    public ResponseEntity<Page<JoinDto>> search(JoinSearchCondition condition, Pageable pageable) {

        Page<JoinDto> joinDtoPage = joinService.getJoin(condition, pageable);

        if (joinDtoPage.hasContent()) {
            return ResponseEntity.status(HttpStatus.OK).body(joinDtoPage);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<JoinDto> newJoin(@RequestBody @Validated JoinDto joinDto, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<JoinDto> optionalStudyJoinDto = joinService.addJoin(joinDto);

        return  optionalStudyJoinDto.map(studyJoin -> ResponseEntity.status(HttpStatus.CREATED).body(studyJoin)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping
    public ResponseEntity<JoinDto> updateJoin(@RequestBody @Validated JoinDto joinDto, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<JoinDto> updatedJoin = joinService.updateJoin(joinDto);

        return  updatedJoin.map(join -> ResponseEntity.status(HttpStatus.OK).body(join)).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
