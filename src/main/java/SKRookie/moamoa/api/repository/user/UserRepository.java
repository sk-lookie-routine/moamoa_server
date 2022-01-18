package SKRookie.moamoa.api.repository.user;

import SKRookie.moamoa.api.entity.user.User;
import SKRookie.moamoa.api.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);

    User findByEmailAndUserType(String email, UserType userType);
}
