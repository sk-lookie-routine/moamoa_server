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

import java.util.List;

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
                        studyTypeIn(condition.getStudyTypeList()),
                        studyHashTagContains(condition.getHashTag()),
                        titleInclude(condition.getTitle()),
                        studySeqEq(condition.getStudySeq())
                );
        return pagingUtil.getPageImpl(pageable, query, Study.class);
    }
    private BooleanExpression userIdEq(Long user_id) {

        return user_id != null ? study.studyUser.userSeq.eq(user_id) : null;
    }

    private BooleanExpression studyTypeIn(List<StudyType> studyType) {

        return studyType != null ? study.studyType.in(studyType) : null;
    }

    private BooleanExpression studyHashTagContains(String hashTag) {

        return hashTag != null ? study.hashTags.any().contains(hashTag) : null;
    }

    private BooleanExpression titleInclude(String title) {

        return title != null ? study.title.contains(title) : null;
    }

    private BooleanExpression studySeqEq(Long study_seq) {

        return study_seq != null ? study.studySeq.eq(study_seq) : null;
    }
}