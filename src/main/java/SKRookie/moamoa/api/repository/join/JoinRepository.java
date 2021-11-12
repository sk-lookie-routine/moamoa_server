package SKRookie.moamoa.api.repository.join;

import SKRookie.moamoa.api.entity.join.Join;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoinRepository extends JpaRepository<Join, Long> {
}

