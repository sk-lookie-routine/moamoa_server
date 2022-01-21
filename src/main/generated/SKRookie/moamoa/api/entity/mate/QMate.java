package SKRookie.moamoa.api.entity.mate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMate is a Querydsl query type for Mate
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMate extends EntityPathBase<Mate> {

    private static final long serialVersionUID = 605447364L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMate mate = new QMate("mate");

    public final NumberPath<Long> mateSeq = createNumber("mateSeq", Long.class);

    public final SKRookie.moamoa.api.entity.study.QStudy mateStudy;

    public final EnumPath<SKRookie.moamoa.api.enums.MateType> mateType = createEnum("mateType", SKRookie.moamoa.api.enums.MateType.class);

    public final SKRookie.moamoa.api.entity.user.QUser mateUser;

    public QMate(String variable) {
        this(Mate.class, forVariable(variable), INITS);
    }

    public QMate(Path<? extends Mate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMate(PathMetadata metadata, PathInits inits) {
        this(Mate.class, metadata, inits);
    }

    public QMate(Class<? extends Mate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mateStudy = inits.isInitialized("mateStudy") ? new SKRookie.moamoa.api.entity.study.QStudy(forProperty("mateStudy"), inits.get("mateStudy")) : null;
        this.mateUser = inits.isInitialized("mateUser") ? new SKRookie.moamoa.api.entity.user.QUser(forProperty("mateUser")) : null;
    }

}

