package SKRookie.moamoa.repository;

import SKRookie.moamoa.entity.User;
import SKRookie.moamoa.type.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findCategoriesByParentIdAndDomainId(Long parent_id, Long domain_id);

    List<User> findCategoriesByCategoryTypeAndTitleNotNullAndDomainId(UserType userType, Long domain_id);

    User findCategoryByIdAndDomainId(Long id, Long domain_id);

    @Query(value = "select title from category where domain_id = :domain_id and id in (:idList)", nativeQuery = true)
    List<String> findTitleByDomainIdAndIdIn(@Param("domain_id") Long domain_id, @Param("idList") List<Long> idList);
}