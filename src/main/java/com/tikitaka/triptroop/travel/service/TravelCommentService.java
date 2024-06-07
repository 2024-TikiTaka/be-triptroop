package com.tikitaka.triptroop.travel.service;


import com.tikitaka.triptroop.common.exception.ForbiddenException;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.travel.domain.entity.TravelComment;
import com.tikitaka.triptroop.travel.domain.repository.TravelCommentRepository;
import com.tikitaka.triptroop.travel.dto.request.TravelCommentRequest;
import com.tikitaka.triptroop.travel.dto.response.TravelCommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class TravelCommentService {

    private final TravelCommentRepository travelCommentRepository;

    /* 페이징 처리 */
    private Pageable getPageable(final Integer page) {

        return PageRequest.of(page - 1, 10, Sort.by("id").descending());
    }

    /* 댓글 등록 */
    @Transactional
    public Long save(Long travelId, Long userId, String content) {

        final TravelComment newComment = TravelComment.of(
                travelId,
                userId,
                content
        );

        final TravelComment travelComment = travelCommentRepository.save(newComment);

        return travelComment.getId();
    }

    /* 댓글 조회 */
    @Transactional(readOnly = true)
    public Page<TravelCommentResponse> findAll(final Integer page, final Long travelId) {

        return travelCommentRepository.findByTravelId(getPageable(page), travelId);
    }

    /* 댓글 수정 */
    @Transactional
    public void updateComment(Long userId, Long commentId, TravelCommentRequest commentRequest) {

        if (!travelCommentRepository.existsByUserIdAndId(userId, commentId)) {
            throw new ForbiddenException(ExceptionCode.ACCESS_DENIED_POST);
        }

        TravelComment comment = travelCommentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_COMMENT));

        comment.update(
                commentRequest.getTravelId(),
                commentRequest.getContent()
        );
    }

    /* 댓글 삭제 */
    @Transactional
    public void deleteTravelComment(Long userId, Long commentId) {

        if (!travelCommentRepository.existsByUserIdAndId(userId, commentId)) {
            throw new ForbiddenException(ExceptionCode.ACCESS_DENIED_POST);
        }

        travelCommentRepository.deleteById(commentId);
    }
}
