package SKRookie.moamoa.api.repository.post;

import SKRookie.moamoa.api.dto.PostSearchCondition;
import SKRookie.moamoa.api.entity.post.Post;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    PageImpl<Post> search(PostSearchCondition condition, Pageable pageable);
}
