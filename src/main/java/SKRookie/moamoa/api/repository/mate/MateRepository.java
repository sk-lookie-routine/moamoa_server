package SKRookie.moamoa.api.repository.mate;

import SKRookie.moamoa.api.entity.mate.Mate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateRepository extends JpaRepository<Mate, Long>, MateRepositoryCustom {
}

