package SKRookie.moamoa.api.repository.study;

import SKRookie.moamoa.api.dto.StudySearchCondition;
import SKRookie.moamoa.api.entity.study.Study;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface StudyCustomRepository {
    Page<Study> search(StudySearchCondition condition, Pageable pageable);
}
