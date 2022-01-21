package SKRookie.moamoa.api.repository.mate;

import SKRookie.moamoa.api.dto.MateSearchCondition;
import SKRookie.moamoa.api.entity.mate.Mate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MateRepositoryCustom {
    Page<Mate> search(MateSearchCondition condition, Pageable pageable);
}
