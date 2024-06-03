package com.tikitaka.triptroop.travel.service;


import com.tikitaka.triptroop.travel.domain.repository.TravelCommentRepository;
import com.tikitaka.triptroop.travel.domain.repository.TravelRepository;
import com.tikitaka.triptroop.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TravelCommentService {

    private final TravelCommentRepository travelCommentRepository;
    private final TravelRepository travelRepository;
    private final UserRepository userRepository;


//    public Long save(TravelCommentRequest commentRequest) {
//        Travel travel = travelRepository.findById(commentRequest.getTravelId())
//                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_TRAVEL));
//        User user = userRepository.findById(commentRequest.getUserId())
//                .orElseThrow(() -> new NotFoundException(ExceptionCode.NOT_FOUND_USER));
//
//        final TravelComment newComment = TravelComment.of(
//                travel,
//                user,
//                commentRequest.getContent()
//        );
//
//        final TravelComment travelComment = travelCommentRepository.save(newComment);
//
//        return travelComment.getId();
//    }
}
