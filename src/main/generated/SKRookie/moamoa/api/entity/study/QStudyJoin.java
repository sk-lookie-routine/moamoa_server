package SKRookie.moamoa.api.entity.study;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudyJoin is a Querydsl query type for StudyJoin
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudyJoin extends EntityPathBase<StudyJoin> {

    private static final long serialVersionUID = 1587474532L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudyJoin studyJoin1 = new QStudyJoin("studyJoin1");

    public final EnumPath<SKRookie.moamoa.api.enums.JoinType> joinType = createEnum("joinType", SKRookie.moamoa.api.enums.JoinType.class);

    public final QStudy studyJoin;

    public final NumberPath<Long> studyJoinSeq = createNumber("studyJoinSeq", Long.class);

    public final SKRookie.moamoa.api.entity.user.QUser studyJoinUser;

    public QStudyJoin(String variable) {
        this(StudyJoin.class, forVariable(variable), INITS);
    }

    public QStudyJoin(Path<? extends StudyJoin> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudyJoin(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudyJoin(PathMetadata metadata, PathInits inits) {
        this(StudyJoin.class, metadata, inits);
    }

    public QStudyJoin(Class<? extends StudyJoin> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.studyJoin = inits.isInitialized("studyJoin") ? new QStudy(forProperty("studyJoin"), inits.get("studyJoin")) : null;
        this.studyJoinUser = inits.isInitialized("studyJoinUser") ? new SKRookie.moamoa.api.entity.user.QUser(forProperty("studyJoinUser")) : null;
    }

}

