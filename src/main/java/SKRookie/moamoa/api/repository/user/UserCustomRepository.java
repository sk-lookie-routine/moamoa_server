package SKRookie.moamoa.api.repository.user;

import SKRookie.moamoa.api.dto.UserSearchCondition;
import SKRookie.moamoa.api.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserCustomRepository {
    Page<User> search(UserSearchCondition condition, Pageable pageable);
}
