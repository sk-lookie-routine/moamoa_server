package SKRookie.moamoa.api.entity.post;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = 1944595876L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPost post = new QPost("post");

    public final StringPath comment = createString("comment");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DatePath<java.time.LocalDate> deadLine = createDate("deadLine", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final StringPath goal = createString("goal");

    public final SetPath<String, StringPath> hashTags = this.<String, StringPath>createSet("hashTags", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath how = createString("how");

    public final StringPath image = createString("image");

    public final StringPath info = createString("info");

    public final NumberPath<Long> memberCount = createNumber("memberCount", Long.class);

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public final ListPath<SKRookie.moamoa.api.entity.join.Join, SKRookie.moamoa.api.entity.join.QJoin> postJoins = this.<SKRookie.moamoa.api.entity.join.Join, SKRookie.moamoa.api.entity.join.QJoin>createList("postJoins", SKRookie.moamoa.api.entity.join.Join.class, SKRookie.moamoa.api.entity.join.QJoin.class, PathInits.DIRECT2);

    public final ListPath<SKRookie.moamoa.api.entity.reply.Reply, SKRookie.moamoa.api.entity.reply.QReply> postReplys = this.<SKRookie.moamoa.api.entity.reply.Reply, SKRookie.moamoa.api.entity.reply.QReply>createList("postReplys", SKRookie.moamoa.api.entity.reply.Reply.class, SKRookie.moamoa.api.entity.reply.QReply.class, PathInits.DIRECT2);

    public final NumberPath<Long> postSeq = createNumber("postSeq", Long.class);

    public final EnumPath<SKRookie.moamoa.api.enums.PostType> postType = createEnum("postType", SKRookie.moamoa.api.enums.PostType.class);

    public final SKRookie.moamoa.api.entity.user.QUser postUser;

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final StringPath title = createString("title");

    public QPost(String variable) {
        this(Post.class, forVariable(variable), INITS);
    }

    public QPost(Path<? extends Post> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPost(PathMetadata metadata, PathInits inits) {
        this(Post.class, metadata, inits);
    }

    public QPost(Class<? extends Post> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.postUser = inits.isInitialized("postUser") ? new SKRookie.moamoa.api.entity.user.QUser(forProperty("postUser")) : null;
    }

}

