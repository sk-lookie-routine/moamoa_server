package SKRookie.moamoa.api.repository.study;

import SKRookie.moamoa.api.dto.StudySearchCondition;
import SKRookie.moamoa.api.entity.study.Study;
import SKRookie.moamoa.api.enums.StudyType;
import SKRookie.moamoa.utils.PagingUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static SKRookie.moamoa.api.entity.study.QStudy.study;

@Repository
public class StudyRepositoryImpl implements StudyRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    private final PagingUtil pagingUtil;

    public StudyRepositoryImpl(JPAQueryFactory jpaQueryFactory, PagingUtil pagingUtil) {
        this.jpaQueryFactory = jpaQueryFactory;
        this.pagingUtil = pagingUtil;
    }

    @Override
    public PageImpl<Study> search(StudySearchCondition condition, Pageable pageable) {
        JPQLQuery<Study> query = jpaQueryFactory       // 1)
                .selectFrom(study)
                .where(
                        userIdEq(condition.getUserSeq()),
                        studyTypeIn(condition.getStudyTypeList()),
                        studyHashTagOrTitleContains(condition.getSearch()),
                        studySeqEq(condition.getStudySeq())
                )
                .orderBy(study.createdAt.desc());
        return pagingUtil.getPageImpl(pageable, query, Study.class);
    }

    private BooleanExpression userIdEq(Long user_id) {

        return user_id != null ? study.studyUser.userSeq.eq(user_id) : null;
    }

    private BooleanExpression studyTypeIn(List<StudyType> studyType) {

        return studyType != null ? study.studyType.in(studyType) : null;
    }

    private BooleanExpression studyHashTagOrTitleContains(String search) {

        return search != null ? study.hashTags.any().contains(search).or(study.title.contains(search)) : null;
    }

    private BooleanExpression studySeqEq(Long study_seq) {

        return study_seq != null ? study.studySeq.eq(study_seq) : null;
    }
}