package SKRookie.moamoa.api.repository.join;

import SKRookie.moamoa.api.dto.JoinSearchCondition;
import SKRookie.moamoa.api.entity.join.Join;
import SKRookie.moamoa.api.enums.JoinType;
import SKRookie.moamoa.utils.PagingUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static SKRookie.moamoa.api.entity.join.QJoin.join;


@Repository
public class JoinCustomRepositoryImpl implements JoinCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private final PagingUtil pagingUtil;

    public JoinCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory, PagingUtil pagingUtil) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.pagingUtil = pagingUtil;
    }

    @Override
    public Page<Join> search(JoinSearchCondition condition, Pageable pageable) {
        JPQLQuery<Join> query = jpaQueryFactory       // 1)
                .selectFrom(join)
                .where(
                        userIdEq(condition.getUserSeq()),
                        studyIdEq(condition.getStudySeq()),
                        joinTypeEq(condition.getJoinType())
                );
        return pagingUtil.getPageImpl(pageable, query, Join.class);
    }
    private BooleanExpression userIdEq(Long user_id) {
        return user_id != null ? join.joinUser.userSeq.eq(user_id) : null;
    }

    private BooleanExpression studyIdEq(Long study_id) {
        return study_id != null ? join.joinStudy.studySeq.eq(study_id) : null;
    }

    private BooleanExpression joinTypeEq(JoinType joinType) {

        return joinType != null ? join.joinType.eq(joinType) : null;
    }
}