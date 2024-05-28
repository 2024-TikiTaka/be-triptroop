package com.tikitaka.triptroop.travel.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTravelComment is a Querydsl query type for TravelComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTravelComment extends EntityPathBase<TravelComment> {

    private static final long serialVersionUID = -1812572932L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTravelComment travelComment = new QTravelComment("travelComment");

    public final com.tikitaka.triptroop.common.domain.QBaseTimeEntity _super = new com.tikitaka.triptroop.common.domain.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final QTravel travelId;

    public final com.tikitaka.triptroop.user.domain.entity.QUser userId;

    public QTravelComment(String variable) {
        this(TravelComment.class, forVariable(variable), INITS);
    }

    public QTravelComment(Path<? extends TravelComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTravelComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTravelComment(PathMetadata metadata, PathInits inits) {
        this(TravelComment.class, metadata, inits);
    }

    public QTravelComment(Class<? extends TravelComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.travelId = inits.isInitialized("travelId") ? new QTravel(forProperty("travelId")) : null;
        this.userId = inits.isInitialized("userId") ? new com.tikitaka.triptroop.user.domain.entity.QUser(forProperty("userId")) : null;
    }

}

