//package com.tikitaka.triptroop.notice.controller;
//
//import com.tikitaka.triptroop.common.dto.response.ApiResponse;
//import com.tikitaka.triptroop.notice.domain.entity.Notice;
//import com.tikitaka.triptroop.notice.dto.response.NoticeDetailResponse;
//import com.tikitaka.triptroop.notice.dto.response.NoticeListResponse;
//import com.tikitaka.triptroop.notice.service.NoticeService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/notice")
//@RequiredArgsConstructor
//@Slf4j
//public class NoticeController {
//    private final NoticeService noticeService;
//
//    /* 공지 조회 */
//    @GetMapping
//    public ResponseEntity<ApiResponse<NoticeListResponse>> getNotice() {
//        NoticeListResponse noticeList = noticeService.getAllNotices();
//        return ResponseEntity.ok(ApiResponse.success(noticeList));
//    }
//
//    /* 공지 상세 조회 */
//    @GetMapping("/{noticeId}")
//    public ResponseEntity<ApiResponse<NoticeDetailResponse>> getNoticeDetail(@PathVariable Long noticeId) {
//        NoticeDetailResponse noticeDetail = noticeService.getNoticeDetail(noticeId);
//        return ResponseEntity.ok(ApiResponse.success(noticeDetail));
//    }
//
//
//}
