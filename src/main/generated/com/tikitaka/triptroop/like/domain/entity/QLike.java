package com.tikitaka.triptroop.like.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLike is a Querydsl query type for Like
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLike extends EntityPathBase<Like> {

    private static final long serialVersionUID = -313558013L;

    public static final QLike like = new QLike("like1");

    public final NumberPath<Long> companionId = createNumber("companionId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.tikitaka.triptroop.common.domain.type.ContentKind> kind = createEnum("kind", com.tikitaka.triptroop.common.domain.type.ContentKind.class);

    public final NumberPath<Long> scheduleId = createNumber("scheduleId", Long.class);

    public final NumberPath<Long> travelId = createNumber("travelId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QLike(String variable) {
        super(Like.class, forVariable(variable));
    }

    public QLike(Path<? extends Like> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLike(PathMetadata metadata) {
        super(Like.class, metadata);
    }

}

