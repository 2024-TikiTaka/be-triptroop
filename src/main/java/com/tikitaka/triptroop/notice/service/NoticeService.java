//package com.tikitaka.triptroop.notice.service;
//
//import com.tikitaka.triptroop.notice.domain.entity.Notice;
//import com.tikitaka.triptroop.notice.domain.repository.NoticeRepository;
//import com.tikitaka.triptroop.notice.dto.response.NoticeListResponse;
//import com.tikitaka.triptroop.notification.domain.repository.NotificationRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class NoticeService {
//    private final NoticeRepository noticeRepository;
//
//    public NoticeListResponse getAllNotices() {
//        List<NoticeListResponse> notices = noticeRepository.findAll()
//                .stream()
//                .map(NoticeListResponse::from)
//                .collect(Collectors.toList());
//        return new NoticeListResponse.from(notices);
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
