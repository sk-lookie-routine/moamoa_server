package SKRookie.moamoa.api.repository.study;

import SKRookie.moamoa.api.dto.StudySearchCondition;
import SKRookie.moamoa.api.entity.study.Study;
import SKRookie.moamoa.api.enums.StudyType;
import SKRookie.moamoa.utils.PagingUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static SKRookie.moamoa.api.entity.study.QStudy.study;

@Repository
public class StudyCustomRepositoryImpl implements StudyCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    private final PagingUtil pagingUtil;

    public StudyCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory, PagingUtil pagingUtil) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.pagingUtil = pagingUtil;
    }

    @Override
    public Page<Study> search(StudySearchCondition condition, Pageable pageable) {
        JPQLQuery<Study> query = jpaQueryFactory       // 1)
                .selectFrom(study)
                .where(
                        userIdEq(condition.getUserSeq()),
                        studyTypeEq(condition.getStudyType())
                );
        return pagingUtil.getPageImpl(pageable, query, Study.class);
    }
    private BooleanExpression userIdEq(Long user_id) {
        return user_id != null ? study.studyUser.userSeq.eq(user_id) : null;
    }

    private BooleanExpression studyTypeEq(StudyType studyType) {

        return studyType != null ? study.studyType.eq(studyType) : null;
    }
}