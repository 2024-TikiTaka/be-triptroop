package com.tikitaka.triptroop.user.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1106085763L;

    public static final QUser user = new QUser("user");

    public final com.tikitaka.triptroop.common.domain.QBaseTimeEntity _super = new com.tikitaka.triptroop.common.domain.QBaseTimeEntity(this);

    public final DatePath<java.time.LocalDate> birth = createDate("birth", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final EnumPath<com.tikitaka.triptroop.user.domain.type.Gender> gender = createEnum("gender", com.tikitaka.triptroop.user.domain.type.Gender.class);

    public final NumberPath<Integer> godo = createNumber("godo", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isMatched = createBoolean("isMatched");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final EnumPath<com.tikitaka.triptroop.user.domain.type.UserRole> role = createEnum("role", com.tikitaka.triptroop.user.domain.type.UserRole.class);

    public final EnumPath<com.tikitaka.triptroop.user.domain.type.UserStatus> status = createEnum("status", com.tikitaka.triptroop.user.domain.type.UserStatus.class);

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

