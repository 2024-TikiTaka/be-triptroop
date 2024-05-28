package com.tikitaka.triptroop.schedule.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QScheduleItem is a Querydsl query type for ScheduleItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QScheduleItem extends EntityPathBase<ScheduleItem> {

    private static final long serialVersionUID = 206852534L;

    public static final QScheduleItem scheduleItem = new QScheduleItem("scheduleItem");

    public final com.tikitaka.triptroop.common.domain.QBaseTimeEntity _super = new com.tikitaka.triptroop.common.domain.QBaseTimeEntity(this);

    public final StringPath content = createString("content");

    public final NumberPath<Integer> cost = createNumber("cost", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final DateTimePath<java.time.LocalDateTime> deletedAt = createDateTime("deletedAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isDeleted = createBoolean("isDeleted");

    public final EnumPath<com.tikitaka.triptroop.schedule.domain.type.ScheduleItemKind> kind = createEnum("kind", com.tikitaka.triptroop.schedule.domain.type.ScheduleItemKind.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Long> placeId = createNumber("placeId", Long.class);

    public final DatePath<java.time.LocalDate> planDate = createDate("planDate", java.time.LocalDate.class);

    public final NumberPath<Long> scheduleId = createNumber("scheduleId", Long.class);

    public QScheduleItem(String variable) {
        super(ScheduleItem.class, forVariable(variable));
    }

    public QScheduleItem(Path<? extends ScheduleItem> path) {
        super(path.getType(), path.getMetadata());
    }

    public QScheduleItem(PathMetadata metadata) {
        super(ScheduleItem.class, metadata);
    }

}

