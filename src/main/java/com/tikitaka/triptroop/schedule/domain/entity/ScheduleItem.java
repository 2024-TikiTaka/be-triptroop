package com.tikitaka.triptroop.schedule.domain.entity;

import com.tikitaka.triptroop.schedule.domain.type.ScheduleType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedules_items") // <- ""안에 엔티티 매핑할 테이블 명 적어주세요.
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ScheduleItem { // <- Entity 를 본인의 엔티티 명으로 바꿔 주세요. ( ! 첫문자 대문자임 (ex-Member O / member X) )

    /* 매핑할 필드( 컬럼명 : 카멜케이스 )를 적어주세요. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "scheduleId")
    private Schedule schedule;

}