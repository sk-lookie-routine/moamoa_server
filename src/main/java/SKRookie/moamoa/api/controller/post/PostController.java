package SKRookie.moamoa.api.controller.post;

import SKRookie.moamoa.api.dto.PostDto;
import SKRookie.moamoa.api.dto.PostSearchCondition;
import SKRookie.moamoa.api.dto.StudyDto;
import SKRookie.moamoa.api.dto.StudySearchCondition;
import SKRookie.moamoa.api.enums.StudyType;
import SKRookie.moamoa.api.service.join.JoinService;
import SKRookie.moamoa.api.service.post.PostService;
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
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<Page<PostDto>> search(PostSearchCondition condition, Pageable pageable) {

        // mypage 에서 요청했을 때와 전체 목록에서 요청했을 때 서로 다른 study를 보내줘야 한다.
        Page<PostDto> post = postService.search(condition, pageable);

        if (post.hasContent()) {
            return ResponseEntity.status(HttpStatus.OK).body(post);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<PostDto> newPost(@RequestBody @Validated PostDto postDto, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        postDto.setCreatedAt(LocalDateTime.now());

        Optional<PostDto> postDto1 = postService.addPost(postDto);

        return  postDto1.map(post -> ResponseEntity.status(HttpStatus.CREATED).body(post)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping()
    public ResponseEntity<PostDto> updatePost(@RequestBody @Validated PostDto postDto, Errors errors){
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        // update one
        Optional<PostDto> optionalPostDto = postService.updatePost(postDto);

        return  optionalPostDto.map(post -> ResponseEntity.status(HttpStatus.OK).body(post)).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
