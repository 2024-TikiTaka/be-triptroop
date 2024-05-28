package com.tikitaka.triptroop.user.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSocialUser is a Querydsl query type for SocialUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSocialUser extends EntityPathBase<SocialUser> {

    private static final long serialVersionUID = -1654789488L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSocialUser socialUser = new QSocialUser("socialUser");

    public final StringPath accessToken = createString("accessToken");

    public final DateTimePath<java.time.LocalDateTime> expiredAt = createDateTime("expiredAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.tikitaka.triptroop.user.domain.type.Provider> provider = createEnum("provider", com.tikitaka.triptroop.user.domain.type.Provider.class);

    public final StringPath refreshToken = createString("refreshToken");

    public final QUser user;

    public QSocialUser(String variable) {
        this(SocialUser.class, forVariable(variable), INITS);
    }

    public QSocialUser(Path<? extends SocialUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSocialUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSocialUser(PathMetadata metadata, PathInits inits) {
        this(SocialUser.class, metadata, inits);
    }

    public QSocialUser(Class<? extends SocialUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

