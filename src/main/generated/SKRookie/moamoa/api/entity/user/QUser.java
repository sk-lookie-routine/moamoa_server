package SKRookie.moamoa.api.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 281309316L;

    public static final QUser user = new QUser("user");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final StringPath image = createString("image");

    public final StringPath userId = createString("userId");

    public final StringPath userInfo = createString("userInfo");

    public final ListPath<SKRookie.moamoa.api.entity.join.Join, SKRookie.moamoa.api.entity.join.QJoin> userJoins = this.<SKRookie.moamoa.api.entity.join.Join, SKRookie.moamoa.api.entity.join.QJoin>createList("userJoins", SKRookie.moamoa.api.entity.join.Join.class, SKRookie.moamoa.api.entity.join.QJoin.class, PathInits.DIRECT2);

    public final ListPath<SKRookie.moamoa.api.entity.mate.Mate, SKRookie.moamoa.api.entity.mate.QMate> userMate = this.<SKRookie.moamoa.api.entity.mate.Mate, SKRookie.moamoa.api.entity.mate.QMate>createList("userMate", SKRookie.moamoa.api.entity.mate.Mate.class, SKRookie.moamoa.api.entity.mate.QMate.class, PathInits.DIRECT2);

    public final StringPath username = createString("username");

    public final ListPath<SKRookie.moamoa.api.entity.reply.Reply, SKRookie.moamoa.api.entity.reply.QReply> userReplys = this.<SKRookie.moamoa.api.entity.reply.Reply, SKRookie.moamoa.api.entity.reply.QReply>createList("userReplys", SKRookie.moamoa.api.entity.reply.Reply.class, SKRookie.moamoa.api.entity.reply.QReply.class, PathInits.DIRECT2);

    public final NumberPath<Long> userSeq = createNumber("userSeq", Long.class);

    public final EnumPath<SKRookie.moamoa.api.enums.UserType> userType = createEnum("userType", SKRookie.moamoa.api.enums.UserType.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

