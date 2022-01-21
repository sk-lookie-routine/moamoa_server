package SKRookie.moamoa.api.controller.mate;

import SKRookie.moamoa.api.dto.MateDto;
import SKRookie.moamoa.api.dto.MateSearchCondition;
import SKRookie.moamoa.api.service.mate.MateService;
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
@RequestMapping("/api/mate")
@RequiredArgsConstructor
public class MateController {

    private final MateService mateService;

    @GetMapping
    public ResponseEntity<Page<MateDto>> search(MateSearchCondition condition, Pageable pageable) {

        Page<MateDto> mateDtoPage = mateService.search(condition, pageable);

        if (mateDtoPage.hasContent()) {
            return ResponseEntity.status(HttpStatus.OK).body(mateDtoPage);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<MateDto> newMate(@RequestBody @Validated MateDto mateDto, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<MateDto> optionalStudyMateDto = mateService.addMate(mateDto);

        return  optionalStudyMateDto.map(mate -> ResponseEntity.status(HttpStatus.CREATED).body(mate)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping
    public ResponseEntity<MateDto> updateMate(@RequestBody @Validated MateDto mateDto, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<MateDto> updatedMate = mateService.updateMate(mateDto);

        return  updatedMate.map(mate -> ResponseEntity.status(HttpStatus.OK).body(mate)).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
