package SKRookie.moamoa.api.repository.mate;

import SKRookie.moamoa.api.dto.MateSearchCondition;
import SKRookie.moamoa.api.entity.join.Join;
import SKRookie.moamoa.api.entity.mate.Mate;
import SKRookie.moamoa.api.enums.JoinType;
import SKRookie.moamoa.api.enums.MateType;
import SKRookie.moamoa.utils.PagingUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static SKRookie.moamoa.api.entity.mate.QMate.mate;


@Repository
public class MateRepositoryImpl implements MateRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    private final PagingUtil pagingUtil;

    public MateRepositoryImpl(JPAQueryFactory jpaQueryFactory, PagingUtil pagingUtil) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.pagingUtil = pagingUtil;
    }

    @Override
    public Page<Mate> search(MateSearchCondition condition, Pageable pageable) {
        JPQLQuery<Mate> query = jpaQueryFactory       // 1)
                .selectFrom(mate)
                .where(
                        userIdEq(condition.getUserSeq()),
                        studyIdEq(condition.getStudySeq()),
                        mateTypeEq(condition.getMateType())
                );
        return pagingUtil.getPageImpl(pageable, query, Mate.class);
    }
    private BooleanExpression userIdEq(Long user_id) {
        return user_id != null ? mate.mateUser.userSeq.eq(user_id) : null;
    }

    private BooleanExpression studyIdEq(Long study_id) {
        return study_id != null ? mate.mateStudy.studySeq.eq(study_id) : null;
    }

    private BooleanExpression mateTypeEq(MateType mateType) {

        return mateType != null ? mate.mateType.eq(mateType) : null;
    }
}