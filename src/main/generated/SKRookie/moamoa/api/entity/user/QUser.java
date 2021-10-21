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

    public final StringPath emailVerifiedYn = createString("emailVerifiedYn");

    public final DateTimePath<java.time.LocalDateTime> modifiedAt = createDateTime("modifiedAt", java.time.LocalDateTime.class);

    public final StringPath password = createString("password");

    public final StringPath profileImageUrl = createString("profileImageUrl");

    public final EnumPath<SKRookie.moamoa.oauth.entity.ProviderType> providerType = createEnum("providerType", SKRookie.moamoa.oauth.entity.ProviderType.class);

    public final EnumPath<SKRookie.moamoa.oauth.entity.RoleType> roleType = createEnum("roleType", SKRookie.moamoa.oauth.entity.RoleType.class);

    public final ListPath<SKRookie.moamoa.api.entity.study.Study, SKRookie.moamoa.api.entity.study.QStudy> studies = this.<SKRookie.moamoa.api.entity.study.Study, SKRookie.moamoa.api.entity.study.QStudy>createList("studies", SKRookie.moamoa.api.entity.study.Study.class, SKRookie.moamoa.api.entity.study.QStudy.class, PathInits.DIRECT2);

    public final ListPath<SKRookie.moamoa.api.entity.study.StudyJoin, SKRookie.moamoa.api.entity.study.QStudyJoin> studyJoins = this.<SKRookie.moamoa.api.entity.study.StudyJoin, SKRookie.moamoa.api.entity.study.QStudyJoin>createList("studyJoins", SKRookie.moamoa.api.entity.study.StudyJoin.class, SKRookie.moamoa.api.entity.study.QStudyJoin.class, PathInits.DIRECT2);

    public final StringPath userId = createString("userId");

    public final StringPath username = createString("username");

    public final NumberPath<Long> userSeq = createNumber("userSeq", Long.class);

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

