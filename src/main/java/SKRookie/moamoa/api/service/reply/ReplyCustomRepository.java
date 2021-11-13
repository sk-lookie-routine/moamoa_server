package SKRookie.moamoa.api.service.reply;

import SKRookie.moamoa.api.dto.ReplySearchCondition;
import SKRookie.moamoa.api.entity.reply.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReplyCustomRepository {
    Page<Reply> search(ReplySearchCondition condition, Pageable pageable);
}
