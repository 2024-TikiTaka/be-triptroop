package com.tikitaka.triptroop.schedule.domain.entity;

import com.tikitaka.triptroop.common.domain.entity.BaseTimeEntity;
import com.tikitaka.triptroop.common.domain.type.RequestStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedules_participants") // <- ""안에 엔티티 매핑할 테이블 명 적어주세요.
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ScheduleParticipants extends BaseTimeEntity { // <- Entity 를 본인의 엔티티 명으로 바꿔 주세요. ( ! 첫문자 대문자임 (ex-Member O / member X) )

    /* 매핑할 필드( 컬럼명 : 카멜케이스 )를 적어주세요. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long userId;
    @ManyToOne
    @JoinColumn(name = "scheduleId")
    private Schedule schedule;
    private Double reviewPoint;
    private String reviewContent;
    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.REQUESTED;
    private String cause;
    private LocalDateTime processedAt;


}
