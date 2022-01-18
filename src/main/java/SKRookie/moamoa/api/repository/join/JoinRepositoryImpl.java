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
public class JoinRepositoryImpl implements JoinRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    private final PagingUtil pagingUtil;

    public JoinRepositoryImpl(JPAQueryFactory jpaQueryFactory, PagingUtil pagingUtil) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.pagingUtil = pagingUtil;
    }

    @Override
    public Page<Join> search(JoinSearchCondition condition, Pageable pageable) {
        JPQLQuery<Join> query = jpaQueryFactory       // 1)
                .selectFrom(join)
                .where(
                        userIdEq(condition.getUserSeq()),
                        postIdEq(condition.getPostSeq()),
                        joinTypeEq(condition.getJoinType())
                );
        return pagingUtil.getPageImpl(pageable, query, Join.class);
    }
    private BooleanExpression userIdEq(Long user_id) {
        return user_id != null ? join.joinUser.userSeq.eq(user_id) : null;
    }

    private BooleanExpression postIdEq(Long post_id) {
        return post_id != null ? join.joinPost.postSeq.eq(post_id) : null;
    }

    private BooleanExpression joinTypeEq(JoinType joinType) {

        return joinType != null ? join.joinType.eq(joinType) : null;
    }
}