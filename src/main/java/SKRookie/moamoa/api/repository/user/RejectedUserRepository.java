package SKRookie.moamoa.api.repository.user;

import SKRookie.moamoa.api.entity.user.RejectedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RejectedUserRepository extends JpaRepository<RejectedUser, Long> {
    RejectedUser findByEmail(String email);
}
