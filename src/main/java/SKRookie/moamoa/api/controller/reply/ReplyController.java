package SKRookie.moamoa.api.controller.reply;

import SKRookie.moamoa.api.dto.*;
import SKRookie.moamoa.api.service.join.JoinService;
import SKRookie.moamoa.api.service.reply.ReplyService;
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
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping()
    public ResponseEntity<Page<ReplyDto>> search(ReplySearchCondition condition, Pageable pageable) {

        Page<ReplyDto> replyDtoPage = replyService.getReply(condition, pageable);

        if (replyDtoPage.hasContent()) {
            return ResponseEntity.status(HttpStatus.OK).body(replyDtoPage);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<ReplyDto> newReply(@RequestBody @Validated ReplyDto replyDto, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<ReplyDto> optionalReplyDto = replyService.addReply(replyDto);

        return  optionalReplyDto.map(reply -> ResponseEntity.status(HttpStatus.CREATED).body(reply)).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}

