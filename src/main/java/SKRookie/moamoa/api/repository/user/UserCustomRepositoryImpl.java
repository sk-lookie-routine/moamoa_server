package SKRookie.moamoa.api.repository.user;

import SKRookie.moamoa.api.dto.UserSearchCondition;
import SKRookie.moamoa.api.entity.study.Study;
import SKRookie.moamoa.api.entity.user.User;
import SKRookie.moamoa.utils.PagingUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static SKRookie.moamoa.api.entity.user.QUser.user;

@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private final PagingUtil pagingUtil;

    public UserCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory, PagingUtil pagingUtil) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.pagingUtil = pagingUtil;
    }

    @Override
    public Page<User> search(UserSearchCondition condition, Pageable pageable) {
        JPAQuery<User> query = jpaQueryFactory       // 1)
                .selectFrom(user)
                .where(
                        userSeqEq(condition.getUserSeq()),
                        userIdEq(condition.getUserId()),
                        usernameEq(condition.getUsername())
                );
        return pagingUtil.getPageImpl(pageable, query, Study.class);
    }
    private BooleanExpression userSeqEq(Long user_seq) {

        return user_seq != null ? user.userSeq.eq(user_seq) : null;
    }

    private BooleanExpression userIdEq(String user_id) {

        return user_id != null ? user.userId.eq(user_id) : null;
    }

    private BooleanExpression usernameEq(String username) {

        return username != null ? user.username.eq(username) : null;
    }
}