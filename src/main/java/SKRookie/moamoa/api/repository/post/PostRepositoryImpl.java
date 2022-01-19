package SKRookie.moamoa.api.repository.post;

import SKRookie.moamoa.api.dto.PostSearchCondition;
import SKRookie.moamoa.api.entity.post.Post;
import SKRookie.moamoa.api.enums.PostType;
import SKRookie.moamoa.utils.PagingUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import static SKRookie.moamoa.api.entity.post.QPost.post;

public class PostRepositoryImpl implements PostRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    private final PagingUtil pagingUtil;

    public PostRepositoryImpl(JPAQueryFactory jpaQueryFactory, PagingUtil pagingUtil) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.pagingUtil = pagingUtil;
    }

    @Override
    public PageImpl<Post> search(PostSearchCondition condition, Pageable pageable) {
        JPQLQuery<Post> query = jpaQueryFactory       // 1)
                .selectFrom(post)
                .where(
                        userIdEq(condition.getUserSeq()),
                        postTypeEq(condition.getPostType()),
                        postHashTagOrTitleContains(condition.getSearch()),
                        postSeqEq(condition.getPostSeq())
                )
                .orderBy(post.createdAt.desc());
        return pagingUtil.getPageImpl(pageable, query, Post.class);
    }

    private BooleanExpression userIdEq(Long user_id) {

        return user_id != null ? post.postUser.userSeq.eq(user_id) : null;
    }

    private BooleanExpression postTypeEq(PostType postType) {

        return postType != null ? post.postType.eq(postType) : null;
    }

    private BooleanExpression postHashTagOrTitleContains(String search) {

        return search != null ? post.hashTags.any().contains(search).or(post.title.contains(search)) : null;
    }

    private BooleanExpression postSeqEq(Long post_seq) {

        return post_seq != null ? post.postSeq.eq(post_seq) : null;
    }
}
