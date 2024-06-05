package com.tikitaka.triptroop.schedule.domain.repository;

import com.tikitaka.triptroop.schedule.domain.entity.ScheduleParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ScheduleParticipantRepository extends JpaRepository<ScheduleParticipant, Long> {
    List<ScheduleParticipant> findByScheduleId(Long scheduleId);

    Optional<ScheduleParticipant> findFirstByScheduleId(Long scheduleId);

    //    @Query("SELECT COUNT(sp) FROM ScheduleParticipant sp WHERE sp.scheduleId = :scheduleId")
//    Long countByScheduleId(@Param("scheduleId") Long scheduleId);
    @Query("SELECT COUNT(sp) FROM ScheduleParticipant sp WHERE sp.scheduleId = :scheduleId AND sp.status = 'ACCEPTED'")
    Long countByScheduleIdAndStatusAccepted(@Param("scheduleId") Long scheduleId);

}
