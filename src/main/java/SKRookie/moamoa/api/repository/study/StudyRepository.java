package SKRookie.moamoa.api.repository.study;

import SKRookie.moamoa.api.entity.study.Study;
import SKRookie.moamoa.api.entity.user.User;
import SKRookie.moamoa.api.enums.StudyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudyRepository extends JpaRepository<Study, Long>, StudyRepositoryCustom {
    List<Study> findAllByStudyUser(User user);

    @Transactional
    List<Study> deleteAllByStudyUser(User user);

    @Query(value = "select * from moamoa.study as s where s.study_seq in (SELECT study_seq FROM moamoa.joins as j where j.user_seq = :user_seq and j.join_Type='APPROVED') and s.study_type = :study_type union select * from moamoa.study as p where p.user_seq = :user_seq and p.study_type = :study_type order by startDate", nativeQuery = true)
    Page<Study> findAllInStudyRoom(@Param("user_seq") Long user_seq, @Param("study_type") String study_type ,Pageable pageable);

    @Query(value = "select * from moamoa.study as s where s.study_seq in (SELECT study_seq FROM moamoa.joins as j where j.user_seq = :user_seq) order by startDate", nativeQuery = true)
    Page<Study> findAllInMyPage(@Param("user_seq") Long user_seq ,Pageable pageable);
}
