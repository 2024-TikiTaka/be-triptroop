package com.tikitaka.triptroop.schedule.service;


import com.tikitaka.triptroop.schedule.domain.entity.ScheduleParticipant;
import com.tikitaka.triptroop.schedule.domain.repository.ScheduleParticipantRepository;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleParticipantRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ScheduleParticipantService {


    private final ScheduleParticipantRepository scheduleParticipantRepository;


    public Long save(@Valid ScheduleParticipantRequest scheduleParticipantRequest, Long scheduleId, Long userId) {
        final ScheduleParticipant newScheduleParticipants = ScheduleParticipant.of(
                scheduleId,
                userId,
                scheduleParticipantRequest.getProcessedAt(),
                scheduleParticipantRequest.getStatus(),
                scheduleParticipantRequest.getCreatedAt()
        );
        final ScheduleParticipant scheduleParticipant = scheduleParticipantRepository.save(newScheduleParticipants);

        return scheduleParticipant.getId();
    }
}

