package SKRookie.moamoa.api.repository.reply;


import SKRookie.moamoa.api.entity.reply.Reply;
import SKRookie.moamoa.api.entity.study.Study;
import SKRookie.moamoa.api.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByReplyUser(User replyUser);

    List<Reply> findAllByReplyStudy(Study replyStudy);

    @Transactional
    List<Reply> deleteAllByReplyUser(User replyUser);

    @Transactional
    List<Reply> deleteAllByReplyStudy(Study replyStudy);
}
