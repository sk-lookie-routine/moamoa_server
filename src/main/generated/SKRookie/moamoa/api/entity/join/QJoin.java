package SKRookie.moamoa.api.entity.join;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJoin is a Querydsl query type for Join
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJoin extends EntityPathBase<Join> {

    private static final long serialVersionUID = -552635420L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJoin join = new QJoin("join1");

    public final StringPath comment = createString("comment");

    public final NumberPath<Long> joinSeq = createNumber("joinSeq", Long.class);

    public final SKRookie.moamoa.api.entity.study.QStudy joinStudy;

    public final EnumPath<SKRookie.moamoa.api.enums.JoinType> joinType = createEnum("joinType", SKRookie.moamoa.api.enums.JoinType.class);

    public final SKRookie.moamoa.api.entity.user.QUser joinUser;

    public QJoin(String variable) {
        this(Join.class, forVariable(variable), INITS);
    }

    public QJoin(Path<? extends Join> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QJoin(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QJoin(PathMetadata metadata, PathInits inits) {
        this(Join.class, metadata, inits);
    }

    public QJoin(Class<? extends Join> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.joinStudy = inits.isInitialized("joinStudy") ? new SKRookie.moamoa.api.entity.study.QStudy(forProperty("joinStudy"), inits.get("joinStudy")) : null;
        this.joinUser = inits.isInitialized("joinUser") ? new SKRookie.moamoa.api.entity.user.QUser(forProperty("joinUser")) : null;
    }

}

