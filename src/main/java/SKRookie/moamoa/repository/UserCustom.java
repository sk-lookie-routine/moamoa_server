package SKRookie.moamoa.repository;

import SKRookie.moamoa.dto.UserSearchCondition;
import SKRookie.moamoa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserCustom {
    Page<User> search(UserSearchCondition condition, Pageable pageable);
}