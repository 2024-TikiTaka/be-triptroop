package com.tikitaka.triptroop.common.exception.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionCode {

    FAIL_TO_UPLOAD_FILE(1001, "파일 저장에 실패하였습니다."),
    FAIL_TO_DELETE_FILE(1002, "파일 삭제에 실패하였습니다."),

    /* 401 Unauthorized */
    UNAUTHORIZED(4002, "인증 되지 않은 요청입니다."),

    /* 403 Forbidden */
    ACCESS_DENIED(4003, "허가 되지 않은 요청입니다."),

    /* 404, NotFoundException */
    NOT_FOUND_REFRESH_TOKEN(4001, "리프레시 토큰이 유효하지 않습니다."),
    NOT_FOUND_USER(4002, "회원 정보를 찾을 수 없습니다."),
    NOT_FOUND_CATEGORY(1201, "해당 카테고리가 존재하지 않습니다."),
    NOT_FOUND_AREA(2001, "해당 지역이 존재하지 않습니다."),
    NOT_FOUND_PLACE(2002, "해당 장소가 존재하지 않습니다."),
    NOT_FOUND_IMAGE(2003, "해당 이미지가 존재하지 않습니다."),
    NOT_FOUND_TRAVEL(2004, "해당 여행지 소개글이 없습니다."),
    NOT_FOUND_REPORT(2005, "신고 내역이 존재하지 않습니다."),
    NOT_FOUND_BLOCK(2006, "차단 내역이 존재하지 않습니다."),
    FAIL_LOGIN(4000, "로그인에 실패하였습니다."),
    NOT_FOUND_COMMENT(2007, "댓글이 존재 하지 않습니다."),
    NOT_FOUND_SCHEDULE(2008, "해당 일정이 존재하지 않습니다."),
    NOT_FOUND_SCHEDULE_ITEM(2009, "해당 계획이 존재하지 않습니다."),
    /* 409, ConflictException */
    ALREADY_EXISTS_EMAIL(2000, "이미 존재하는 이메일입니다."),
    ALREADY_EXIST_REVIEW(6000, "후기 작성내역이 존재합니다.");

    private final int code;

    private final String message;
}
