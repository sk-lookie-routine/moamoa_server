package SKRookie.moamoa.api.entity.study;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStudy is a Querydsl query type for Study
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QStudy extends EntityPathBase<Study> {

    private static final long serialVersionUID = -1928643558L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStudy study = new QStudy("study");

    public final StringPath comment = createString("comment");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DatePath<java.time.LocalDate> deadLine = createDate("deadLine", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final StringPath goal = createString("goal");

    public final ListPath<String, StringPath> hashTags = this.<String, StringPath>createList("hashTags", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath how = createString("how");

    public final StringPath image = createString("image");

    public final StringPath info = createString("info");

    public final StringPath linkChat = createString("linkChat");

    public final StringPath linkNotion = createString("linkNotion");

    public final StringPath linkStudy = createString("linkStudy");

    public final NumberPath<Long> memberCount = createNumber("memberCount", Long.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final ListPath<SKRookie.moamoa.api.entity.join.Join, SKRookie.moamoa.api.entity.join.QJoin> studyJoins = this.<SKRookie.moamoa.api.entity.join.Join, SKRookie.moamoa.api.entity.join.QJoin>createList("studyJoins", SKRookie.moamoa.api.entity.join.Join.class, SKRookie.moamoa.api.entity.join.QJoin.class, PathInits.DIRECT2);

    public final ListPath<SKRookie.moamoa.api.entity.reply.Reply, SKRookie.moamoa.api.entity.reply.QReply> studyReplys = this.<SKRookie.moamoa.api.entity.reply.Reply, SKRookie.moamoa.api.entity.reply.QReply>createList("studyReplys", SKRookie.moamoa.api.entity.reply.Reply.class, SKRookie.moamoa.api.entity.reply.QReply.class, PathInits.DIRECT2);

    public final NumberPath<Long> studySeq = createNumber("studySeq", Long.class);

    public final EnumPath<SKRookie.moamoa.api.enums.StudyType> studyType = createEnum("studyType", SKRookie.moamoa.api.enums.StudyType.class);

    public final SKRookie.moamoa.api.entity.user.QUser studyUser;

    public final StringPath title = createString("title");

    public QStudy(String variable) {
        this(Study.class, forVariable(variable), INITS);
    }

    public QStudy(Path<? extends Study> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStudy(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStudy(PathMetadata metadata, PathInits inits) {
        this(Study.class, metadata, inits);
    }

    public QStudy(Class<? extends Study> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.studyUser = inits.isInitialized("studyUser") ? new SKRookie.moamoa.api.entity.user.QUser(forProperty("studyUser")) : null;
    }

}

