package SKRookie.moamoa.api.repository.reply;


import SKRookie.moamoa.api.entity.reply.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
