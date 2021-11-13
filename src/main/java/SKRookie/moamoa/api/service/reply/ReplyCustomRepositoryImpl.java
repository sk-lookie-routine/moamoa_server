package SKRookie.moamoa.api.service.reply;

import SKRookie.moamoa.api.dto.ReplySearchCondition;
import SKRookie.moamoa.api.dto.StudySearchCondition;
import SKRookie.moamoa.api.entity.reply.Reply;
import SKRookie.moamoa.api.entity.study.Study;
import SKRookie.moamoa.api.enums.StudyType;
import SKRookie.moamoa.utils.PagingUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static SKRookie.moamoa.api.entity.reply.QReply.reply;

@Repository
public class ReplyCustomRepositoryImpl implements ReplyCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    private final PagingUtil pagingUtil;

    public ReplyCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory, PagingUtil pagingUtil) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.pagingUtil = pagingUtil;
    }

    @Override
    public Page<Reply> search(ReplySearchCondition condition, Pageable pageable) {

        JPQLQuery<Reply> query = jpaQueryFactory
                .selectFrom(reply)
                .where(
                        studySeqEq(condition.getStudySeq()),
                        userSeqEq(condition.getUserSeq()),
                        contentInclude(condition.getContent())
                )
                .orderBy(reply.createdAt.asc());

        return pagingUtil.getPageImpl(pageable, query, Reply.class);
    }
    private BooleanExpression studySeqEq(Long study_seq) {

        return study_seq != null ? reply.replyStudy.studySeq.eq(study_seq) : null;
    }

    private BooleanExpression userSeqEq(Long user_seq) {

        return user_seq != null ? reply.replyUser.userSeq.eq(user_seq) : null;
    }

    private BooleanExpression contentInclude(String content) {

        return content != null ? reply.content.contains(content) : null;
    }
}