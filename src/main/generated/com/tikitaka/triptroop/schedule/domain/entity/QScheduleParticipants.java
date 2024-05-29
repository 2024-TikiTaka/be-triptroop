package com.tikitaka.triptroop.schedule.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QScheduleParticipants is a Querydsl query type for ScheduleParticipants
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QScheduleParticipants extends EntityPathBase<ScheduleParticipants> {

    private static final long serialVersionUID = -260080829L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QScheduleParticipants scheduleParticipants = new QScheduleParticipants("scheduleParticipants");

    public final com.tikitaka.triptroop.common.domain.QBaseTimeEntity _super = new com.tikitaka.triptroop.common.domain.QBaseTimeEntity(this);

    public final StringPath cause = createString("cause");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final DateTimePath<java.time.LocalDateTime> processedAt = createDateTime("processedAt", java.time.LocalDateTime.class);

    public final StringPath reviewContent = createString("reviewContent");

    public final NumberPath<Double> reviewPoint = createNumber("reviewPoint", Double.class);

    public final QSchedule schedule;

    public final EnumPath<com.tikitaka.triptroop.common.domain.type.RequestStatus> status = createEnum("status", com.tikitaka.triptroop.common.domain.type.RequestStatus.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QScheduleParticipants(String variable) {
        this(ScheduleParticipants.class, forVariable(variable), INITS);
    }

    public QScheduleParticipants(Path<? extends ScheduleParticipants> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QScheduleParticipants(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QScheduleParticipants(PathMetadata metadata, PathInits inits) {
        this(ScheduleParticipants.class, metadata, inits);
    }

    public QScheduleParticipants(Class<? extends ScheduleParticipants> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.schedule = inits.isInitialized("schedule") ? new QSchedule(forProperty("schedule"), inits.get("schedule")) : null;
    }

}

