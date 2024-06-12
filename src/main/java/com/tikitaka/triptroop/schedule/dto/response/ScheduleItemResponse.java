//package com.tikitaka.triptroop.schedule.dto.response;
//
//import com.tikitaka.triptroop.place.domain.entity.Place;
//import com.tikitaka.triptroop.schedule.domain.entity.ScheduleItem;
//import com.tikitaka.triptroop.schedule.domain.type.ScheduleItemKind;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Getter
//@RequiredArgsConstructor
//public class ScheduleItemResponse {
//
//
//    private Long scheduleId;
//
//    private String placeName;
//
//    private LocalDate planDate;
//
//    private ScheduleItemKind kind;
//
//    private Integer cost;
//
//    private String content;
//
//    public ScheduleItemResponse(Long scheduleId, LocalDate planDate, ScheduleItemKind kind, Integer cost, String content, Place place) {
//        this.scheduleId = scheduleId;
//        this.planDate = planDate;
//        this.kind = kind;
//        this.cost = cost;
//        this.content = content;
//        this.placeName = place.address + " " + place.name;
//    }
//
////
////    public static ScheduleItemResponse from(ScheduleItem scheduleItem) {
////        return new ScheduleItemResponse(
////                scheduleItem.getScheduleId(),
////                scheduleItem.getPlanDate(),
////                scheduleItem.getKind(),
////                scheduleItem.getCost(),
////                scheduleItem.getContent(),
////                scheduleItem.getPlace()
////        );
////    }
////
////    public static List<ScheduleItemResponse> from(List<ScheduleItem> scheduleItem) {
////        return scheduleItem.stream()
////                .map(ScheduleItemResponse::from)
////                .collect(Collectors.toList());
////    }
//}
