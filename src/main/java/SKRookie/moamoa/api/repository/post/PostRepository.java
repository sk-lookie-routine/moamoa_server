package SKRookie.moamoa.api.repository.post;

import SKRookie.moamoa.api.entity.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
}
