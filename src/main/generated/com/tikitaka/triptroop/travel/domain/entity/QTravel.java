package com.tikitaka.triptroop.travel.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTravel is a Querydsl query type for Travel
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTravel extends EntityPathBase<Travel> {

    private static final long serialVersionUID = 16686051L;

    public static final QTravel travel = new QTravel("travel");

    public final com.tikitaka.triptroop.common.domain.QBaseTimeEntity _super = new com.tikitaka.triptroop.common.domain.QBaseTimeEntity(this);

    public final NumberPath<Long> areaId = createNumber("areaId", Long.class);

    public final NumberPath<Long> categoryId = createNumber("categoryId", Long.class);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.tikitaka.triptroop.image.domain.entity.Image, com.tikitaka.triptroop.image.domain.entity.QImage> images = this.<com.tikitaka.triptroop.image.domain.entity.Image, com.tikitaka.triptroop.image.domain.entity.QImage>createList("images", com.tikitaka.triptroop.image.domain.entity.Image.class, com.tikitaka.triptroop.image.domain.entity.QImage.class, PathInits.DIRECT2);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Long> placeId = createNumber("placeId", Long.class);

    public final StringPath title = createString("title");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final NumberPath<Integer> views = createNumber("views", Integer.class);

    public final EnumPath<com.tikitaka.triptroop.common.domain.type.Visibility> visibility = createEnum("visibility", com.tikitaka.triptroop.common.domain.type.Visibility.class);

    public QTravel(String variable) {
        super(Travel.class, forVariable(variable));
    }

    public QTravel(Path<? extends Travel> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTravel(PathMetadata metadata) {
        super(Travel.class, metadata);
    }

}

