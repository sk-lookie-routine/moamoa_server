package SKRookie.moamoa.api.repository.join;

import SKRookie.moamoa.api.dto.joinSearchCondition;
import SKRookie.moamoa.api.entity.join.Join;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudyJoinCustomRepository {
    Page<Join> search(joinSearchCondition condition, Pageable pageable);
}
