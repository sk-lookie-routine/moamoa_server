package SKRookie.moamoa.api.repository.study;

import SKRookie.moamoa.api.entity.study.Study;
import SKRookie.moamoa.api.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long> {
    List<Study> findAllByStudyUser(User user);
    @Transactional
    List<Study> deleteAllByStudyUser(User user);
}
