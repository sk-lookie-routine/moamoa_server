package SKRookie.moamoa.api.repository.join;

import SKRookie.moamoa.api.dto.JoinSearchCondition;
import SKRookie.moamoa.api.entity.join.Join;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JoinRepositoryCustom {
    Page<Join> search(JoinSearchCondition condition, Pageable pageable);
}
