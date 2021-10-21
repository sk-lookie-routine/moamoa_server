package SKRookie.moamoa.api.repository.study;

import SKRookie.moamoa.api.entity.study.Study;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long> {
}
