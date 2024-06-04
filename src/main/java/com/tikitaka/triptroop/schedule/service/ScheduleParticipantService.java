package com.tikitaka.triptroop.schedule.service;


import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.schedule.domain.entity.ScheduleParticipant;
import com.tikitaka.triptroop.schedule.domain.repository.ScheduleParticipantRepository;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleParticipantAcceptRequest;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleParticipantRejectedRequest;
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

    public void accept(ScheduleParticipantAcceptRequest scheduleParticipantAcceptRequest, Long scheduleParticipantId) {

        ScheduleParticipant scheduleParticipant = scheduleParticipantRepository.findById(scheduleParticipantId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));
        scheduleParticipant.update(
                scheduleParticipantAcceptRequest.getStatus(),
                scheduleParticipantAcceptRequest.getProcessedAt()
        );
    }

    public void reject(ScheduleParticipantRejectedRequest scheduleParticipantRejectedRequest, Long scheduleParticipantId) {
        ScheduleParticipant scheduleParticipant = scheduleParticipantRepository.findById(scheduleParticipantId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));
        scheduleParticipant.rejected(
                scheduleParticipantRejectedRequest.getStatus(),
                scheduleParticipantRejectedRequest.getProcessedAt(),
                scheduleParticipantRejectedRequest.getCause()
        );
    }
}

