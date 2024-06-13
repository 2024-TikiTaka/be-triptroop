package com.tikitaka.triptroop.schedule.service;


import com.tikitaka.triptroop.common.domain.type.RequestStatus;
import com.tikitaka.triptroop.common.exception.ForbiddenException;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.schedule.domain.entity.Schedule;
import com.tikitaka.triptroop.schedule.domain.entity.ScheduleParticipant;
import com.tikitaka.triptroop.schedule.domain.repository.ScheduleParticipantRepository;
import com.tikitaka.triptroop.schedule.domain.repository.ScheduleRepository;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleParticipantRejectedRequest;
import com.tikitaka.triptroop.schedule.dto.request.ScheduleReviewRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ScheduleParticipantService {


    private final ScheduleParticipantRepository scheduleParticipantRepository;
    private final ScheduleRepository scheduleRepository;


    // TODO 신청
    public Long save(Long scheduleId, Long userId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).get();
        Long participants = scheduleParticipantRepository.countByScheduleIdAndStatusAccepted(scheduleId);
        int count = schedule.getCount();
        LocalDate applyDate = LocalDate.now();
        LocalDate startDate = schedule.getStartDate();
        if (participants == count) {
            throw new ForbiddenException(ExceptionCode.ACCESS_DENIED_COUNT);
        } else if (applyDate.isEqual(startDate)) {
            throw new ForbiddenException(ExceptionCode.ACCESS_DENIED_DATE);
        } else if (applyDate.isAfter(startDate)) {
            throw new ForbiddenException(ExceptionCode.ACCESS_DENIED_DATE);
        }

        LocalDateTime processedAt = LocalDateTime.now();
        final ScheduleParticipant newScheduleParticipants = ScheduleParticipant.of(
                scheduleId,
                userId,
                processedAt);

        final ScheduleParticipant scheduleParticipant = scheduleParticipantRepository.save(newScheduleParticipants);

        return scheduleParticipant.getId();
    }

    // TODO 신청 승인
    public void accept(Long scheduleParticipantId) {
        ScheduleParticipant scheduleParticipant = scheduleParticipantRepository.findById(scheduleParticipantId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));
        Schedule schedule = scheduleRepository.findById(scheduleParticipant.getScheduleId()).get();
        RequestStatus status = RequestStatus.ACCEPTED;
        LocalDateTime processedAt = LocalDateTime.now();
        scheduleParticipant.update(
                status,
                processedAt

        );
    }

    // TODO 신청 반려
    public void reject(ScheduleParticipantRejectedRequest scheduleParticipantRejectedRequest, Long scheduleParticipantId) {
        ScheduleParticipant scheduleParticipant = scheduleParticipantRepository.findById(scheduleParticipantId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));
        RequestStatus status = RequestStatus.REJECTED;
        LocalDateTime processedAt = LocalDateTime.now();
        log.info("cause:{}", scheduleParticipantRejectedRequest.getCause());
        scheduleParticipant.rejected(
                status,
                processedAt,
                scheduleParticipantRejectedRequest.getCause()
        );
    }

    public void writeReview(Long scheduleId, Long userId, ScheduleReviewRequest scheduleReviewRequest) {

        ScheduleParticipant scheduleParticipant = scheduleParticipantRepository.findByScheduleIdAndReviewerId(scheduleId, userId);

        if (scheduleParticipant.getStatus() != RequestStatus.ACCEPTED) {
            throw new ForbiddenException(ExceptionCode.ACCESS_DENIED);
        }

        scheduleParticipant.writeReview(
                scheduleReviewRequest.getReviewPoint(),
                scheduleReviewRequest.getReviewContent()
        );

    }

    public void updateReview(Long scheduleId, Long userId, ScheduleReviewRequest scheduleReviewRequest) {

        ScheduleParticipant scheduleParticipant = scheduleParticipantRepository.findByScheduleIdAndReviewerId(scheduleId, userId);

        if (scheduleParticipant.getStatus() != RequestStatus.ACCEPTED) {
            throw new ForbiddenException(ExceptionCode.ACCESS_DENIED);
        }

        scheduleParticipant.updateReview(
                scheduleReviewRequest.getReviewPoint(),
                scheduleReviewRequest.getReviewContent()
        );
    }

    public void removeReview(Long scheduleId, Long userId) {
        ScheduleParticipant scheduleParticipant = scheduleParticipantRepository.findByScheduleIdAndReviewerId(scheduleId, userId);
        Long participantId = scheduleParticipant.getId();
        scheduleParticipantRepository.deleteById(participantId);

    }

    public void saveUser(Long userId, Long scheduleId) {
        RequestStatus status = RequestStatus.ACCEPTED;
        ScheduleParticipant newParticipant = ScheduleParticipant.saveUser(
                userId,
                scheduleId,
                status
        );
        scheduleParticipantRepository.save(newParticipant);
    }
}

