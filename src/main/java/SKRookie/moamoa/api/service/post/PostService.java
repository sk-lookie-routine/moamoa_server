package SKRookie.moamoa.api.service.post;

import SKRookie.moamoa.api.dto.*;
import SKRookie.moamoa.api.entity.post.Post;
import SKRookie.moamoa.api.entity.study.Study;
import SKRookie.moamoa.api.entity.user.User;
import SKRookie.moamoa.api.repository.join.JoinRepository;
import SKRookie.moamoa.api.repository.post.PostRepository;
import SKRookie.moamoa.api.repository.reply.ReplyRepository;
import SKRookie.moamoa.api.repository.study.StudyRepositoryCustom;
import SKRookie.moamoa.api.repository.study.StudyRepository;
import SKRookie.moamoa.api.repository.user.UserRepository;
import SKRookie.moamoa.api.service.join.JoinService;
import SKRookie.moamoa.api.service.reply.ReplyService;
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
public class PostService {
    private final PostRepository postRepository;

    private final ReplyService replyService;

    private final JoinService joinService;

    private final ModelMapper modelMapper;


    public Optional<PostDto> addPost(PostDto postDto) {
        Post map = modelMapper.map(postDto, Post.class);
        //수정 날짜 변경
        map.setModifiedAt(LocalDateTime.now());

        Post save = postRepository.save(map);

        return Optional.of(modelMapper.map(save, PostDto.class));
    }

    public Page<PostDto> search(PostSearchCondition condition, Pageable pageable) {
        PageImpl<Post> search = postRepository.search(condition, pageable);

        List<PostDto> postDtos = search.stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return new PageImpl<>(postDtos, pageable, search.getTotalElements());
    }

    public Optional<PostDto> findOne(PostDto postDto) {
        Optional<Post> findOne = postRepository.findById(postDto.getPostSeq());

        return Optional.of(modelMapper.map(findOne.get(), PostDto.class));
    }

    public Optional<PostDto> updatePost(PostDto postDto) {

        Post post = postRepository.findById(postDto.getPostSeq()).get();

        post.setModifiedAt(LocalDateTime.now());
        post.setEndDate(postDto.getEndDate());
        post.setStartDate(postDto.getStartDate());
        post.setGoal(postDto.getGoal());
        post.setHashTags(postDto.getHashTags());
        post.setHow(postDto.getHow());
        post.setInfo(postDto.getInfo());
        post.setTitle(postDto.getTitle());
        post.setComment(postDto.getComment());
        post.setPostType(postDto.getPostType());
        post.setMemberCount(postDto.getMemberCount());

        postRepository.saveAndFlush(post);

        return Optional.of(modelMapper.map(post, PostDto.class));
    }

    public void deletePost(Long id) {
        replyService.deleteReplysByPostSeq(id);

        joinService.deleteJoinsByPostSeq(id);

        postRepository.deleteById(id);
    }

}
