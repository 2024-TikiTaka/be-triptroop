package com.tikitaka.triptroop.travel.service;


import com.tikitaka.triptroop.common.exception.ForbiddenException;
import com.tikitaka.triptroop.common.exception.NotFoundException;
import com.tikitaka.triptroop.common.exception.type.ExceptionCode;
import com.tikitaka.triptroop.travel.domain.entity.TravelComment;
import com.tikitaka.triptroop.travel.domain.repository.TravelCommentRepository;
import com.tikitaka.triptroop.travel.domain.repository.TravelRepository;
import com.tikitaka.triptroop.travel.dto.request.TravelCommentRequest;
import com.tikitaka.triptroop.travel.dto.response.TravelCommentResponse;
import com.tikitaka.triptroop.user.domain.repository.UserRepository;
import com.tikitaka.triptroop.user.service.ProfileService;
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

    private final UserRepository userRepository;
    private final ProfileService profileService;
    private final TravelRepository travelRepository;


    /* 페이징 처리 */
    private Pageable getPageable(final Integer page) {
        return PageRequest.of(page - 1, 10, Sort.by("id").descending());
    }

    /* 댓글 등록 */
    public Long save(TravelCommentRequest commentRequest, Long userId) {


        final TravelComment newComment = TravelComment.of(
                commentRequest.getTravelId(),
                userId,
                commentRequest.getContent()
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
    public void updateComment(Long userId, Long commentId, TravelCommentRequest commentRequest) {

        if (!travelRepository.existsByUserIdAndId(userId, commentId)) {
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
    public void deleteTravelComment(Long userId, Long commentId) {

        if (!travelRepository.existsByUserIdAndId(userId, commentId)) {
            throw new ForbiddenException(ExceptionCode.ACCESS_DENIED_POST);

        }

        travelCommentRepository.deleteById(commentId);

    }
}
