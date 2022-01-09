package SKRookie.moamoa.api.entity.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRejectedUser is a Querydsl query type for RejectedUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRejectedUser extends EntityPathBase<RejectedUser> {

    private static final long serialVersionUID = 1231470882L;

    public static final QRejectedUser rejectedUser = new QRejectedUser("rejectedUser");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final StringPath userId = createString("userId");

    public final StringPath username = createString("username");

    public final NumberPath<Long> userSeq = createNumber("userSeq", Long.class);

    public QRejectedUser(String variable) {
        super(RejectedUser.class, forVariable(variable));
    }

    public QRejectedUser(Path<? extends RejectedUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRejectedUser(PathMetadata metadata) {
        super(RejectedUser.class, metadata);
    }

}

