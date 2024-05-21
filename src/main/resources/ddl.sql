# create user 'tikitaka'@'%' identified by 'tikitaka2024!';
# GRANT ALL PRIVILEGES ON `triptroop`.* TO `tikitaka`@`%`;

# ===========================================================
create database `triptroop`;

# GRANT ALL PRIVILEGES ON `triptroop`.* TO `ohgiraffers`@`%`;

use triptroop;

CREATE TABLE `users`
(
    `id`    BIGINT NOT NULL COMMENT '회원_코드',
    `email`    VARCHAR(255) NOT NULL COMMENT '이메일',
    `password`    VARCHAR(255) COMMENT '비밀번호',
    `name`    VARCHAR(50) NOT NULL COMMENT '이름',
    `birth`    DATE NOT NULL COMMENT '생년월일',
    `role`    VARCHAR(20) DEFAULT 'ROLE_USER' NOT NULL COMMENT '권한',
    `gender`    VARCHAR(6) NOT NULL COMMENT '성별',
    `phone`    VARCHAR(127) COMMENT '전화번호',
    `godo`    INTEGER DEFAULT 38 NOT NULL COMMENT '고도',
    `sns_provider`    VARCHAR(10) COMMENT '소셜로그인제공자',
    `refresh_token`    VARCHAR(300) NOT NULL COMMENT '리프레시토큰',
    `expired_at`    DATETIME COMMENT '토큰만료일자',
    `status`    VARCHAR(20) DEFAULT 'ACTIVE' NOT NULL COMMENT '상태',
    `created_at`    DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `modified_at`    DATETIME COMMENT '수정일시',
    `deleted_at`    DATETIME COMMENT '탈퇴일시',
    `isMatched`    VARCHAR(10) DEFAULT 'MACHED' NOT NULL COMMENT '매칭여부',
    PRIMARY KEY ( `id` )
) COMMENT = '회원';

ALTER TABLE `users`
    ADD CONSTRAINT `users_CK` CHECK ( `role` IN ('USER','ADMIN') );

ALTER TABLE `users`
    ADD CONSTRAINT `users_CK1` CHECK ( `status` IN ('ACTIVE','SUSPENDED','WITHDRAWN') );


CREATE TABLE `profiles`
(
    `id`    BIGINT NOT NULL COMMENT '프로필_코드',
    `user_id`    BIGINT NOT NULL COMMENT '회원_코드',
    `nickname`    VARCHAR(50) NOT NULL COMMENT '닉네임',
    `profile_image`    VARCHAR(255) COMMENT '프로필이미지',
    `introduction`    VARCHAR(500) COMMENT '자기소개',
    `mbti`    CHAR(4) COMMENT 'MBTI',
    `created_at`    DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `modified_at`    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
        COMMENT '수정일시',
    PRIMARY KEY ( `id` )
) COMMENT = '프로필';
ALTER TABLE `profiles`
    ADD CONSTRAINT `profiles_FK` FOREIGN KEY ( `user_id` )
        REFERENCES `users` (`id` );

CREATE TABLE `areas`
(
    `id`    BIGINT NOT NULL COMMENT '지역_코드',
    `region`    BIGINT NOT NULL COMMENT '시도',
    PRIMARY KEY ( `id` )
) COMMENT = '지역';


CREATE TABLE `categories`
(
    `id`    BIGINT NOT NULL COMMENT '카테고리_코드',
    `name`    BIGINT NOT NULL COMMENT '카테고리_이름',
    `created_at`    DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `deleted_at`    DATETIME COMMENT '삭제일시',
    PRIMARY KEY ( `id` )
) COMMENT = '카테고리';

CREATE TABLE `blocks`
(
    `id`    BIGINT NOT NULL COMMENT '차단_코드',
    `target_id`    BIGINT NOT NULL COMMENT '차단회원_코드',
    `user_id`    BIGINT NOT NULL COMMENT '회원_코드',
    `status`    VARCHAR(10) NOT NULL COMMENT '상태',
    `blocked_at`    DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '차단일시',
    `modified_at`    DATETIME ON UPDATE CURRENT_TIMESTAMP
        COMMENT '단계수정일시',
    `deleted_at`    DATETIME COMMENT '차단해제일시',
    PRIMARY KEY ( `id` )
) COMMENT = '차단';
ALTER TABLE `blocks`
    ADD CONSTRAINT `blocks_FK` FOREIGN KEY ( `user_id` )
        REFERENCES `users` (`id` );
ALTER TABLE `blocks`
    ADD CONSTRAINT `blocks_FK1` FOREIGN KEY ( `target_id` )
        REFERENCES `users` (`id` );

CREATE TABLE `places`
(
    `id`    BIGINT NOT NULL COMMENT '장소_코드',
    `kakaomap_id`    VARCHAR(64) COMMENT '카카오맵장소코드',
    `address` VARCHAR(255)  NOT NULL COMMENT '주소지',
    `name` VARCHAR(255)  COMMENT '장소명',
    `created_at`    DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `modified_at`    DATETIME COMMENT '수정일시',
    `deleted_at`    DATETIME COMMENT '삭제일시',
    PRIMARY KEY ( `id` )
) COMMENT = '장소';

CREATE TABLE `interests`
(
    `id`    BIGINT NOT NULL COMMENT '성향항목_코드',
    `ref_interest_id`    BIGINT COMMENT '성향항목_참조코드',
    `content`    VARCHAR(256) NOT NULL COMMENT '항목내용',
    PRIMARY KEY ( `id` )
) COMMENT = '성향항목';

CREATE TABLE `user_interests`
(
    `id`    BIGINT NOT NULL COMMENT '회원성향_코드',
    `interest_id`    BIGINT NOT NULL COMMENT '성향항목_코드',
    `user_id`    BIGINT NOT NULL COMMENT '회원_코드',
    PRIMARY KEY ( `id` )
) COMMENT = '회원성향';
ALTER TABLE `user_interests`
    ADD CONSTRAINT `user_interests_FK` FOREIGN KEY ( `interest_id` )
        REFERENCES `interests` (`id` );
ALTER TABLE `user_interests`
    ADD CONSTRAINT `user_interests_FK1` FOREIGN KEY ( `user_id` )
        REFERENCES `users` (`id` );

CREATE TABLE `schedules`
(
    `id`    BIGINT NOT NULL COMMENT '일정_코드',
    `user_id`    BIGINT NOT NULL COMMENT '회원_코드',
    `area_id`    BIGINT NOT NULL COMMENT '지역_코드',
    `start_date`    DATE NOT NULL COMMENT '여행시작일',
    `end_date`    DATE NOT NULL COMMENT '여행종료일',
    `title`    VARCHAR(255) NOT NULL COMMENT '제목',
    `count`    INTEGER DEFAULT 1 COMMENT '참여가능인원',
    `status`    VARCHAR(10) DEFAULT 'PUBLIC' NOT NULL COMMENT '공개상태',
    `is_deleted`    VARCHAR(10) DEFAULT 'USABLE' NOT NULL COMMENT '삭제여부',
    `created_at`    DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `modified_at`    DATETIME ON UPDATE CURRENT_TIMESTAMP
        COMMENT '수정일시',
    `deleted_at`    DATETIME COMMENT '삭제일시',
    PRIMARY KEY ( `id` )
) COMMENT = '일정';
ALTER TABLE `schedules`
    ADD CONSTRAINT `schedules_CK` CHECK ( `status` IN ('PUBLIC','PRIVATE') );
ALTER TABLE `schedules`
    ADD CONSTRAINT `schedules_CK1` CHECK ( `is_deleted` IN ('DELETED','USABLE') );
ALTER TABLE `schedules`
    ADD CONSTRAINT `schedules_FK` FOREIGN KEY ( `area_id` )
        REFERENCES `areas` (`id` );
ALTER TABLE `schedules`
    ADD CONSTRAINT `schedules_FK1` FOREIGN KEY ( `user_id` )
        REFERENCES `users` (`id` );


CREATE TABLE `schedule_items`
(
    `id`    BIGINT NOT NULL COMMENT '일정계획_코드',
    `schedule_id`    BIGINT NOT NULL COMMENT '일정_코드',
    `place_id`    BIGINT COMMENT '장소_코드',
    `plan_date`    DATE NOT NULL COMMENT '계획일정일자',
    `kind`    VARCHAR(10) NOT NULL COMMENT '구분',
    `cost`    INTEGER COMMENT '비용',
    `status`    VARCHAR(10) NOT NULL COMMENT '상태',
    `is_deleted`    VARCHAR(10) DEFAULT 'USABLE' NOT NULL COMMENT '삭제여부',
    `created_at`    DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `modified_at`    DATETIME ON UPDATE CURRENT_TIMESTAMP
        COMMENT '수정일시',
    `deleted_at`    DATETIME COMMENT '삭제일시',
    PRIMARY KEY ( `id` )
) COMMENT = '일정항목';
ALTER TABLE `schedule_items`
    ADD CONSTRAINT `schedule_items_CK` CHECK ( `is_deleted` IN ('USABLE','DELETED') );
ALTER TABLE `schedule_items`
    ADD CONSTRAINT `schedule_items_CK1` CHECK ( `status` IN ('PUBLIC','PRIVATE') );
ALTER TABLE `schedule_items`
    ADD CONSTRAINT `schedule_items_FK` FOREIGN KEY ( `place_id` )
        REFERENCES `places` (`id` );
ALTER TABLE `schedule_items`
    ADD CONSTRAINT `schedule_items_FK1` FOREIGN KEY ( `place_id` )
        REFERENCES `places` (`id` );
ALTER TABLE `schedule_items`
    ADD CONSTRAINT `schedule_items_FK2` FOREIGN KEY ( `place_id` )
        REFERENCES `places` (`id` );
ALTER TABLE `schedule_items`
    ADD CONSTRAINT `schedule_items_FK3` FOREIGN KEY ( `schedule_id` )
        REFERENCES `schedules` (`id` );

CREATE TABLE `schedule_participants`
(
    `id`     BIGINT NOT NULL COMMENT '일정참여자_코드',
    `user_id`     BIGINT NOT NULL COMMENT '회원_코드',
    `schedule_id`     BIGINT NOT NULL COMMENT '일정_코드',
    `review_point`    INTEGER COMMENT '일정후기평점',
    `review_content`    VARCHAR(500) COMMENT '일정후기내용',
    `status`    VARCHAR(10) DEFAULT 'REQUESTED' NOT NULL COMMENT '신청상태',
    `cause`    VARCHAR(255) COMMENT '반려사유',
    `created_at`    DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `processed_at`    DATETIME COMMENT '처리일시',
    PRIMARY KEY ( `id` )
) COMMENT = '일정참여자';
ALTER TABLE `schedule_participants`
    ADD CONSTRAINT `schedule_participants_FK` FOREIGN KEY ( `schedule_id` )
        REFERENCES `schedules` (`id` );
ALTER TABLE `schedule_participants`
    ADD CONSTRAINT `schedule_participants_FK1` FOREIGN KEY ( `user_id` )
        REFERENCES `users` (`id` );
ALTER TABLE `schedule_participants`
    ADD CONSTRAINT `schedule_participants_CK` CHECK ( `status` IN ('REQUESTED','APPROVAL','REJECTED') );

CREATE TABLE `travel_logs`
(
    `id`    BIGINT NOT NULL COMMENT '여행기록_코드',
    `user_id`    BIGINT NOT NULL COMMENT '회원_코드',
    `category_id`    BIGINT NOT NULL COMMENT '카테고리_코드',
    `area_id`    BIGINT NOT NULL COMMENT '지역_코드',
    `start_date`    DATE NOT NULL COMMENT '여행시작일',
    `end_date`    DATE NOT NULL COMMENT '여행종료일',
    `title`    VARCHAR(255) NOT NULL COMMENT '제목',
    `status`    VARCHAR(10) DEFAULT 'PRIVATE' NOT NULL COMMENT '공개상태',
    `is_deleted`    VARCHAR(10) DEFAULT 'USABLE' NOT NULL COMMENT '삭제여부',
    `created_at`    DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `modified_at`    DATETIME COMMENT '수정일시',
    `deleted_at`    DATETIME COMMENT '삭제일시',
    PRIMARY KEY ( `id` )
) COMMENT = '여행기록';
ALTER TABLE `travel_logs`
    ADD CONSTRAINT `travel_logs_CK` CHECK ( `status` IN ('PRIVATE','PUBLIC') );
ALTER TABLE `travel_logs`
    ADD CONSTRAINT `travel_logs_CK1` CHECK ( `is_deleted` IN ('USABLE','DELETED') );
ALTER TABLE `travel_logs`
    ADD CONSTRAINT `travel_logs_FK` FOREIGN KEY ( `area_id` )
        REFERENCES `areas` (`id` );
ALTER TABLE `travel_logs`
    ADD CONSTRAINT `travel_logs_FK1` FOREIGN KEY ( `category_id` )
        REFERENCES `categories` (`id` );
ALTER TABLE `travel_logs`
    ADD CONSTRAINT `travel_logs_FK2` FOREIGN KEY ( `user_id` )
        REFERENCES `users` (`id` );

CREATE TABLE `travel_log_items`
(
    `id`    BIGINT NOT NULL COMMENT '여행기록항목_코드',
    `travel_log_id`    BIGINT NOT NULL COMMENT '여행기록_코드',
    `place_id`    BIGINT NOT NULL COMMENT '장소_코드',
    `order`    INTEGER NOT NULL COMMENT '순서',
    `point`    INTEGER NOT NULL COMMENT '별점',
    `content`    VARCHAR(500) NOT NULL COMMENT '내용',
    `travel_date`    DATE NOT NULL COMMENT '여행일자',
    `created_at`    DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `modified_at`    DATETIME ON UPDATE CURRENT_TIMESTAMP
        COMMENT '수정일시',
    `deleted_at`    DATETIME COMMENT '삭제일시',
    PRIMARY KEY ( `id` )
) COMMENT = '여행기록항목';
ALTER TABLE `travel_log_items`
    ADD CONSTRAINT `travel_log_items_FK` FOREIGN KEY ( `place_id` )
        REFERENCES `places` (`id` );
ALTER TABLE `travel_log_items`
    ADD CONSTRAINT `travel_log_items_FK1` FOREIGN KEY ( `travel_log_id` )
        REFERENCES `travel_logs` (`id` );


CREATE TABLE `travels`
(
    `id`    BIGINT NOT NULL COMMENT '여행지소개_코드',
    `user_id`    BIGINT NOT NULL COMMENT '회원_코드',
    `category_id`    BIGINT NOT NULL COMMENT '카테고리_코드',
    `area_id`    BIGINT NOT NULL COMMENT '지역_코드',
    `place_id`    BIGINT NOT NULL COMMENT '장소_코드',
    `title`    VARCHAR(255) NOT NULL COMMENT '제목',
    `content`    BLOB NOT NULL COMMENT '내용',
    `status`    VARCHAR(10) DEFAULT 'PUBLIC' NOT NULL COMMENT '공개상태',
    `is_deleted`    VARCHAR(10) DEFAULT 'USABLE' NOT NULL COMMENT '삭제여부',
    `created_at`    DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `modified_at`    DATETIME ON UPDATE CURRENT_TIMESTAMP
        COMMENT '수정일시',
    `deleted_at`    DATETIME COMMENT '삭제일시',
    PRIMARY KEY ( `id` )
) COMMENT = '여행지소개';

ALTER TABLE `travels`
    ADD CONSTRAINT `travels_CK1` CHECK ( `status` IN ('PUBLIC','PRIVATE') );
ALTER TABLE `travels`
    ADD CONSTRAINT `travels_CK2` CHECK ( `is_deleted` IN ('USABLE','DELETED') );
ALTER TABLE `travels`
    ADD CONSTRAINT `travels_FK` FOREIGN KEY ( `category_id` )
        REFERENCES `categories` (`id` );
ALTER TABLE `travels`
    ADD CONSTRAINT `travels_FK1` FOREIGN KEY ( `area_id` )
        REFERENCES `areas` (`id` );
ALTER TABLE `travels`
    ADD CONSTRAINT `travels_FK2` FOREIGN KEY ( `place_id` )
        REFERENCES `places` (`id` );
ALTER TABLE `travels`
    ADD CONSTRAINT `travels_FK3` FOREIGN KEY ( `user_id` )
        REFERENCES `users` (`id` );

CREATE TABLE `travel_comments`
(
    `id`    BIGINT NOT NULL COMMENT '여행지소개댓글_코드',
    `user_id`    BIGINT NOT NULL COMMENT '회원_코드',
    `travel_id`    BIGINT NOT NULL COMMENT '여행지소개_코드',
    `content`    VARCHAR(500) NOT NULL COMMENT '내용',
    `is_deleted`    VARCHAR(10) DEFAULT 'USABLE' NOT NULL COMMENT '삭제여부',
    `created_at`    DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성시간',
    `modified_at`    DATETIME ON UPDATE CURRENT_TIMESTAMP
        COMMENT '수정시간',
    PRIMARY KEY ( `id` )
) COMMENT = '여행지소개댓글';
ALTER TABLE `travel_comments`
    ADD CONSTRAINT `travel_comments_CK` CHECK ( `is_deleted` IN ('DELETED','USABLE') );
ALTER TABLE `travel_comments`
    ADD CONSTRAINT `travel_comments_FK` FOREIGN KEY ( `travel_id` )
        REFERENCES `travels` (`id` );

CREATE TABLE `companions`
(
    `id`    BIGINT NOT NULL COMMENT '동행글_코드',
    `user_id`    BIGINT NOT NULL COMMENT '회원_코드',
    `schedule_id`    BIGINT NOT NULL COMMENT '일정_코드',
    `title`    VARCHAR(255) NOT NULL COMMENT '제목',
    `content`    BLOB NOT NULL COMMENT '내용',
    `views`    INTEGER DEFAULT 0 NOT NULL COMMENT '조회수',
    `age`    VARCHAR(10) NOT NULL COMMENT '동행글나이',
    `gender`    VARCHAR(10) NOT NULL COMMENT '동행글성별',
    `status`    VARCHAR(10) DEFAULT 'RECRUITING' NOT NULL COMMENT '모집상태',
    `is_deleted`    VARCHAR(10) DEFAULT 'USABLE' NOT NULL COMMENT '삭제여부',
    `created_at`    DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `modified_at`    DATETIME NOT NULL ON UPDATE CURRENT_TIMESTAMP
        COMMENT '수정일시',

    PRIMARY KEY ( `id` )
) COMMENT = '동행글';

ALTER TABLE `companions`
    ADD CONSTRAINT `companions_FK` FOREIGN KEY ( `schedule_id` )
        REFERENCES `schedules` (`id` );

ALTER TABLE `companions`
    ADD CONSTRAINT `companions_FK1` FOREIGN KEY ( `user_id` )
        REFERENCES `users` (`id` );



CREATE TABLE `user_reviews`
(
    `id`    BIGINT NOT NULL COMMENT '회원후기_코드',
    `writer_id`    BIGINT NOT NULL COMMENT '작성자_코드',
    `user_id`    BIGINT NOT NULL COMMENT '회원_코드',
    `companion_id`    BIGINT NOT NULL COMMENT '동행글_코드',
    `point`    INT COMMENT '평점',
    `created_at`    DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `modified_at`    DATETIME ON UPDATE CURRENT_TIMESTAMP
        COMMENT '수정일시',
    `deleted_at`    DATETIME COMMENT '삭제일시',
    PRIMARY KEY ( `id` )
) COMMENT = '회원후기';
ALTER TABLE `user_reviews`
    ADD CONSTRAINT `user_reviews_FK` FOREIGN KEY ( `companion_id` )
        REFERENCES `companions` (`id` );
ALTER TABLE `user_reviews`
    ADD CONSTRAINT `user_reviews_FK1` FOREIGN KEY ( `user_id` )
        REFERENCES `users` (`id` );
ALTER TABLE `user_reviews`
    ADD CONSTRAINT `user_reviews_FK2` FOREIGN KEY ( `writer_id` )
        REFERENCES `users` (`id` );

CREATE TABLE `friends`
(
    `id`    BIGINT NOT NULL COMMENT '친구_코드',
    `user_id`    BIGINT NOT NULL COMMENT '회원_코드',
    `friend_user_id`    BIGINT NOT NULL COMMENT '친구회원_코드',
    `status`    VARCHAR(20) NOT NULL COMMENT '친구상태',
    PRIMARY KEY ( `id` )
) COMMENT = '친구';
ALTER TABLE `friends`
    ADD CONSTRAINT `friends_CK` CHECK ( `status` IN ('REQUESTED','APPROVAL','REJECTED') );
ALTER TABLE `friends`
    ADD CONSTRAINT `friends_FK` FOREIGN KEY ( `user_id` )
        REFERENCES `users` (`id` );
ALTER TABLE `friends`
    ADD CONSTRAINT `friends_FK1` FOREIGN KEY ( `friend_user_id` )
        REFERENCES `users` (`id` );


CREATE TABLE `inquiries`
(
    `id`    BIGINT NOT NULL COMMENT '문의_코드',
    `user_code`    BIGINT NOT NULL COMMENT '회원_코드',
    `kind`    VARCHAR(10) DEFAULT 'N' NOT NULL COMMENT '분류',
    `content`    VARCHAR(500) COMMENT '내용',
    `reply`    VARCHAR(500) COMMENT '답변',
    `created_at`    DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '등록일시',
    `processed_at`    DATETIME COMMENT '처리일시',
    PRIMARY KEY ( `id` )
) COMMENT = '문의';
ALTER TABLE `inquiries`
    ADD CONSTRAINT `inquiries_CK1` CHECK ( `kind` IN ('INQUIRY','SUGGESTION') );
ALTER TABLE `inquiries`
    ADD CONSTRAINT `inquiries_FK` FOREIGN KEY ( `user_code` )
        REFERENCES `users` (`id` );


CREATE TABLE `likes`
(
    `id`    BIGINT NOT NULL COMMENT '좋아요_코드',
    `user_id`    BIGINT NOT NULL COMMENT '회원_코드',
    `schedule_id`    BIGINT COMMENT '일정_코드',
    `travel_log_id`    BIGINT NOT NULL COMMENT '여행지소개_코드',
    `companion_id`    BIGINT COMMENT '동행글_코드',
    `kind`    VARCHAR(20) COMMENT '분류',
    PRIMARY KEY ( `id` )
) COMMENT = '좋아요';
ALTER TABLE `likes`
    ADD CONSTRAINT `likes_CK` CHECK ( `kind` IN ('SCHEDULE','TRAVEL_LOG','COMPANION') );
ALTER TABLE `likes`
    ADD CONSTRAINT `likes_FK` FOREIGN KEY ( `companion_id` )
        REFERENCES `companions` (`id` );
ALTER TABLE `likes`
    ADD CONSTRAINT `likes_FK1` FOREIGN KEY ( `schedule_id` )
        REFERENCES `schedules` (`id` );
ALTER TABLE `likes`
    ADD CONSTRAINT `likes_FK2` FOREIGN KEY ( `travel_log_id` )
        REFERENCES `travels` (`id` );

CREATE TABLE `notifications`
(
    `id`    BIGINT NOT NULL COMMENT '알림_코드',
    `user_id`    BIGINT NOT NULL COMMENT '회원_코드',
    `kind`    VARCHAR(30) NOT NULL COMMENT '구분',
    `status`    VARCHAR(10) DEFAULT 'N' NOT NULL COMMENT '읽음상태',
    `message`    VARCHAR(500) NOT NULL COMMENT '메시지내용',
    `created_at`    DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '발송일시',
    PRIMARY KEY ( `id` )
) COMMENT = '알림';
ALTER TABLE `notifications`
    ADD CONSTRAINT `notifications_FK` FOREIGN KEY ( `user_id` )
        REFERENCES `users` (`id` );

CREATE TABLE `reports`
(
    `id`    BIGINT NOT NULL COMMENT '신고_코드',
    `kind`    VARCHAR(20) NOT NULL COMMENT '구분',
    `schedule_id`    BIGINT COMMENT '일정_코드',
    `reporter_id`    BIGINT NOT NULL COMMENT '신고자_코드',
    `reportee_id`    BIGINT COMMENT '회원_코드',
    `travel_log_id`    BIGINT COMMENT '여행지소개_코드',
    `companion_id`    BIGINT COMMENT '동행글_코드',
    `type`    VARCHAR(20) NOT NULL COMMENT '신고타입',
    `content`    VARCHAR(500) NOT NULL COMMENT '신고내용',
    `status`    VARCHAR(10) DEFAULT 'REQUESTED' NOT NULL COMMENT '처리상태',
    `created_at`    DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '신고일시',
    `processed_at`    DATETIME COMMENT '처리일시',
    PRIMARY KEY ( `id`,`reporter_id` )
) COMMENT = '신고';
ALTER TABLE `reports`
    ADD CONSTRAINT `reports_CK2` CHECK ( `status` IN ('REQUESTED','COMPLETED') );
ALTER TABLE `reports`
    ADD CONSTRAINT `reports_CK` CHECK ( `kind` IN ('USER','COMPANION','SCHEDULE','TRAVEL') );
ALTER TABLE `reports`
    ADD CONSTRAINT `reports_CK1` CHECK ( `type` IN ('PROFANITY','ADVERTISEMENT','NOMANNER','SEXUAL','REDENESS','HARASSMENT') );
ALTER TABLE `reports`
    ADD CONSTRAINT `reports_FK` FOREIGN KEY ( `companion_id` )
        REFERENCES `companions` (`id` );
ALTER TABLE `reports`
    ADD CONSTRAINT `reports_FK1` FOREIGN KEY ( `schedule_id` )
        REFERENCES `schedules` (`id` );
ALTER TABLE `reports`
    ADD CONSTRAINT `reports_FK2` FOREIGN KEY ( `travel_log_id` )
        REFERENCES `travels` (`id` );
ALTER TABLE `reports`
    ADD CONSTRAINT `reports_FK3` FOREIGN KEY ( `reporter_id` )
        REFERENCES `users` (`id` );
ALTER TABLE `reports`
    ADD CONSTRAINT `reports_FK4` FOREIGN KEY ( `reportee_id` )
        REFERENCES `users` (`id` );

CREATE TABLE `images`
(
    `id`    BIGINT NOT NULL COMMENT '이미지_코드',
    `reporter_id`    BIGINT COMMENT '신고자_코드',
    `travel_id`    BIGINT COMMENT '여행지소개_코드',
    `schedule_id`    BIGINT COMMENT '일정_코드',
    `companion_id`    BIGINT COMMENT '동행글_코드',
    `report_id`    BIGINT COMMENT '신고_코드',
    `inquiry_id`    BIGINT COMMENT '문의_코드',
    `kind`    VARCHAR(30) NOT NULL COMMENT '분류',
    `path`    VARCHAR(255) NOT NULL COMMENT '경로',
    `uuid`    VARCHAR(255) NOT NULL COMMENT '식별파일명',
    `name`    VARCHAR(255) NOT NULL COMMENT '원본파일명',
    `extension`    VARCHAR(255) NOT NULL COMMENT '확장자',
    `created_at`    DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    PRIMARY KEY ( `id` )
) COMMENT = '이미지';
ALTER TABLE `images`
    ADD CONSTRAINT `images_CK` CHECK ( `kind` IN ('IMAGE_TRAVEL','IMAGE_SCHEDULE','IMAGE_COMPANION','IMAGE_REPORT','IMAGE_INQUIRY') );
ALTER TABLE `images`
    ADD CONSTRAINT `images_FK` FOREIGN KEY ( `companion_id` )
        REFERENCES `companions` (`id` );
ALTER TABLE `images`
    ADD CONSTRAINT `images_FK1` FOREIGN KEY ( `inquiry_id` )
        REFERENCES `inquiries` (`id` );
ALTER TABLE `images`
    ADD CONSTRAINT `images_FK2` FOREIGN KEY ( `report_id`,`reporter_id` )
        REFERENCES `reports` (`id`,`reporter_id` );
ALTER TABLE `images`
    ADD CONSTRAINT `images_FK3` FOREIGN KEY ( `schedule_id` )
        REFERENCES `schedules` (`id` );
ALTER TABLE `images`
    ADD CONSTRAINT `images_FK4` FOREIGN KEY ( `travel_id` )
        REFERENCES `travels` (`id` );