package SKRookie.moamoa.api.service.reply;

import SKRookie.moamoa.api.dto.*;
import SKRookie.moamoa.api.entity.join.Join;
import SKRookie.moamoa.api.entity.reply.Reply;
import SKRookie.moamoa.api.repository.reply.ReplyCustomRepository;
import SKRookie.moamoa.api.repository.reply.ReplyRepository;
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
public class ReplyService {

    private final ReplyRepository replyRepository;

    private final ReplyCustomRepository replyCustomRepository;

    private final ModelMapper modelMapper;

    public Optional<ReplyDto> addReply(ReplyDto replyDto) {

        replyDto.setCreatedAt(LocalDateTime.now());

        Reply save = replyRepository.save(modelMapper.map(replyDto, Reply.class));

        return Optional.of(modelMapper.map(save, ReplyDto.class));
    }

    public Page<ReplyDto> getReply(ReplySearchCondition condition, Pageable pageable) {

        Page<Reply> search = replyCustomRepository.search(condition, pageable);

        List<ReplyDto> replyDtos = search.stream().map(reply -> modelMapper.map(reply, ReplyDto.class)).collect(Collectors.toList());

        return new PageImpl<>(replyDtos, pageable, search.getTotalElements());
    }

    public Optional<ReplyDto> updateReply(ReplyDto replyDto) {

        Optional<Reply> byId = replyRepository.findById(replyDto.getReplySeq());

        Reply reply = byId.get();

        reply.setContent(replyDto.getContent());

        replyRepository.saveAndFlush(reply);

        return Optional.of(modelMapper.map(reply, ReplyDto.class));
    }

    public void deleteReply(Long reply_id) {
        replyRepository.deleteById(reply_id);
    }

    public void deleteReplysByPostSeq(Long post_id)  {

        List<Reply> allByReplyPost_postSeq = replyRepository.findAllByReplyPost_PostSeq(post_id);

        allByReplyPost_postSeq.forEach((Reply) -> {
            deleteReply(Reply.getReplySeq());
        });
    }
}

