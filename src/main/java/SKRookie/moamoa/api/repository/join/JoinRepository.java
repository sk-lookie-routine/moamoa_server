package SKRookie.moamoa.api.repository.join;

import SKRookie.moamoa.api.entity.join.Join;
import SKRookie.moamoa.api.entity.study.Study;
import SKRookie.moamoa.api.entity.user.User;
import SKRookie.moamoa.api.enums.JoinType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JoinRepository extends JpaRepository<Join, Long>, JoinRepositoryCustom {
    List<Join> findAllByJoinUser(User user);

    List<Join> findAllByJoinUserAndJoinType(User user, JoinType joinType);

    List<Join> findAllByJoinStudy(Study study);

    @Transactional
    List<Join> deleteAllByJoinUser(User user);

    @Transactional
    List<Join> deleteAllByJoinStudy(Study study);
}

