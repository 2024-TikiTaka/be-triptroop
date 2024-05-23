# ===========================================================
CREATE DATABASE `triptroop`;

# GRANT ALL PRIVILEGES ON `triptroop`.* TO `ohgiraffers`@`%`;

USE triptroop;
# ===========================================================
CREATE TABLE `areas` (
    `id`   BIGINT       NOT NULL COMMENT '지역_코드',
    `sido` VARCHAR(127) NOT NULL COMMENT '시도',
    PRIMARY KEY (`id`)
) COMMENT = '지역';


CREATE TABLE `categories` (
    `id`         BIGINT                             NOT NULL COMMENT '카테고리_코드',
    `name`       BIGINT                             NOT NULL COMMENT '카테고리_이름',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `deleted_at` DATETIME COMMENT '삭제일시',
    PRIMARY KEY (`id`)
) COMMENT = '카테고리';


CREATE TABLE `places` (
    `id`          BIGINT                             NOT NULL COMMENT '장소_코드',
    `kakaomap_id` VARCHAR(255) COMMENT '카카오맵장소코드',
    `address`     VARCHAR(255)                       NOT NULL COMMENT '주소지',
    `name`        VARCHAR(255) COMMENT '장소명',
    `created_at`  DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `modified_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '수정일시',
    `deleted_at`  DATETIME COMMENT '삭제일시',
    PRIMARY KEY (`id`)
) COMMENT = '장소';


CREATE TABLE `users` (
    `id`          BIGINT                                NOT NULL COMMENT '회원_코드',
    `email`       VARCHAR(255)                          NOT NULL COMMENT '이메일',
    `password`    VARCHAR(255) COMMENT '비밀번호',
    `name`        VARCHAR(50)                           NOT NULL COMMENT '이름',
    `birth`       DATE                                  NOT NULL COMMENT '생년월일',
    `role`        VARCHAR(10) DEFAULT 'USER'            NOT NULL COMMENT '권한',
    `gender`      CHAR(1)                               NOT NULL COMMENT '성별',
    `phone`       VARCHAR(127) COMMENT '전화번호',
    `godo`        INT         DEFAULT 38                NOT NULL COMMENT '고도',
    `status`      VARCHAR(10) DEFAULT 'ACTIVE'          NOT NULL COMMENT '상태',
    `is_matched`  TINYINT     DEFAULT 1                 NOT NULL COMMENT '매칭여부',
    `created_at`  DATETIME    DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `modified_at` DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
    `deleted_at`  DATETIME COMMENT '탈퇴일시',
    PRIMARY KEY (`id`)
) COMMENT = '회원';

ALTER TABLE `users`
    ADD CONSTRAINT `users_CK` CHECK ( `role` IN ('USER', 'ADMIN') );

ALTER TABLE `users`
    ADD CONSTRAINT `users_CK1` CHECK ( `status` IN ('ACTIVE', 'SUSPENDED', 'WITHDRAWN') );

ALTER TABLE `users`
    ADD CONSTRAINT `users_CK2` CHECK ( `gender` IN ('F', 'M') );


CREATE TABLE `user_refresh_tokens` (
    `id`            BIGINT       NOT NULL COMMENT '회원리프레시토큰_코드',
    `user_id`       BIGINT       NOT NULL COMMENT '회원_코드',
    `refresh_token` VARCHAR(255) NOT NULL COMMENT '리프레시토큰',
    `expired_at`    DATETIME     NOT NULL COMMENT '만료일자',
    PRIMARY KEY (`id`)
) COMMENT = '유저_리프레시토큰';


CREATE TABLE `interests` (
    `id`     BIGINT       NOT NULL COMMENT '성향항목_코드',
    `name`   VARCHAR(255) NOT NULL COMMENT '항목내용',
    `ref_id` BIGINT COMMENT '성향항목_참조코드',
    PRIMARY KEY (`id`)
) COMMENT = '성향항목';


CREATE TABLE `user_interests` (
    `id`          BIGINT NOT NULL COMMENT '회원성향_코드',
    `interest_id` BIGINT NOT NULL COMMENT '성향항목_코드',
    `user_id`     BIGINT NOT NULL COMMENT '회원_코드',
    PRIMARY KEY (`id`)
) COMMENT = '회원성향';

ALTER TABLE `user_interests`
    ADD CONSTRAINT `user_interests_FK` FOREIGN KEY (`interest_id`)
        REFERENCES `interests` (`id`);

ALTER TABLE `user_interests`
    ADD CONSTRAINT `user_interests_FK1` FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`);


CREATE TABLE `social_users` (
    `id`            BIGINT       NOT NULL COMMENT '소셜로그인_코드',
    `user_id`       BIGINT       NOT NULL COMMENT '회원_코드',
    `provider`      VARCHAR(10)  NOT NULL COMMENT '소셜로그인제공자',
    `access_token`  VARCHAR(255) NOT NULL COMMENT '액세스토큰',
    `refresh_token` VARCHAR(255) NOT NULL COMMENT '리프레시토큰',
    `expired_at`    DATETIME     NOT NULL COMMENT '리프레시토큰만료시간',
    PRIMARY KEY (`id`)
) COMMENT = '소셜로그인';

ALTER TABLE `social_users`
    ADD CONSTRAINT `social_users_CK` CHECK ( `provider` IN ('KAKAO', 'NAVER', 'GOOGLE') );

CREATE TABLE `travels` (
    `id`          BIGINT                                NOT NULL COMMENT '여행지소개_코드',
    `user_id`     BIGINT                                NOT NULL COMMENT '회원_코드',
    `category_id` BIGINT                                NOT NULL COMMENT '카테고리_코드',
    `area_id`     BIGINT                                NOT NULL COMMENT '지역_코드',
    `place_id`    BIGINT                                NOT NULL COMMENT '장소_코드',
    `title`       VARCHAR(255)                          NOT NULL COMMENT '제목',
    `content`     TEXT                                  NOT NULL COMMENT '내용',
    `visibility`  VARCHAR(10) DEFAULT 'PUBLIC'          NOT NULL COMMENT '공개상태',
    `is_deleted`  TINYINT     DEFAULT 0                 NOT NULL COMMENT '삭제여부',
    `created_at`  DATETIME    DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `modified_at` DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
    `deleted_at`  DATETIME COMMENT '삭제일시',
    PRIMARY KEY (`id`)
) COMMENT = '여행지소개';

ALTER TABLE `travels`
    ADD CONSTRAINT `travels_CK` CHECK ( `visibility` IN ('PUBLIC', 'PRIVATE') );

ALTER TABLE `travels`
    ADD CONSTRAINT `travels_FK` FOREIGN KEY (`category_id`)
        REFERENCES `categories` (`id`);

ALTER TABLE `travels`
    ADD CONSTRAINT `travels_FK1` FOREIGN KEY (`area_id`)
        REFERENCES `areas` (`id`);

ALTER TABLE `travels`
    ADD CONSTRAINT `travels_FK2` FOREIGN KEY (`place_id`)
        REFERENCES `places` (`id`);

ALTER TABLE `travels`
    ADD CONSTRAINT `travels_FK3` FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`);


CREATE TABLE `travel_comments` (
    `id`          BIGINT                             NOT NULL COMMENT '여행지소개댓글_코드',
    `user_id`     BIGINT                             NOT NULL COMMENT '회원_코드',
    `travel_id`   BIGINT                             NOT NULL COMMENT '여행지소개_코드',
    `content`     VARCHAR(500)                       NOT NULL COMMENT '내용',
    `is_deleted`  TINYINT  DEFAULT 0                 NOT NULL COMMENT '삭제여부',
    `created_at`  DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성시간',
    `modified_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정시간',
    `deleted_at`  DATETIME COMMENT '삭제시간',
    PRIMARY KEY (`id`)
) COMMENT = '여행지소개댓글';

ALTER TABLE `travel_comments`
    ADD CONSTRAINT `travel_comments_FK` FOREIGN KEY (`travel_id`)
        REFERENCES `travels` (`id`);


CREATE TABLE `travel_logs` (
    `id`          BIGINT                                NOT NULL COMMENT '여행기록_코드',
    `user_id`     BIGINT                                NOT NULL COMMENT '회원_코드',
    `category_id` BIGINT                                NOT NULL COMMENT '카테고리_코드',
    `area_id`     BIGINT                                NOT NULL COMMENT '지역_코드',
    `start_date`  DATE                                  NOT NULL COMMENT '여행시작일',
    `end_date`    DATE                                  NOT NULL COMMENT '여행종료일',
    `title`       VARCHAR(255)                          NOT NULL COMMENT '제목',
    `visibility`  VARCHAR(10) DEFAULT 'PRIVATE'         NOT NULL COMMENT '공개상태',
    `is_deleted`  TINYINT     DEFAULT 0                 NOT NULL COMMENT '삭제상태',
    `created_at`  DATETIME    DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `modified_at` DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
    `deleted_at`  DATETIME COMMENT '삭제일시',
    PRIMARY KEY (`id`)
) COMMENT = '여행기록';

ALTER TABLE `travel_logs`
    ADD CONSTRAINT `travel_logs_CK` CHECK ( `visibility` IN ('PRIVATE', 'PUBLIC') );

ALTER TABLE `travel_logs`
    ADD CONSTRAINT `travel_logs_FK` FOREIGN KEY (`area_id`)
        REFERENCES `areas` (`id`);

ALTER TABLE `travel_logs`
    ADD CONSTRAINT `travel_logs_FK1` FOREIGN KEY (`category_id`)
        REFERENCES `categories` (`id`);

ALTER TABLE `travel_logs`
    ADD CONSTRAINT `travel_logs_FK2` FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`);


CREATE TABLE `travel_log_items` (
    `id`            BIGINT                             NOT NULL COMMENT '여행기록항목_코드',
    `travel_log_id` BIGINT                             NOT NULL COMMENT '여행기록_코드',
    `place_id`      BIGINT                             NOT NULL COMMENT '장소_코드',
    `order`         INT                                NOT NULL COMMENT '순서',
    `point`         INT                                NOT NULL COMMENT '별점',
    `content`       VARCHAR(500)                       NOT NULL COMMENT '내용',
    `travel_date`   DATE                               NOT NULL COMMENT '여행일자',
    `created_at`    DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `modified_at`   DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
    `deleted_at`    DATETIME COMMENT '삭제일시',
    PRIMARY KEY (`id`)
) COMMENT = '여행기록항목';

ALTER TABLE `travel_log_items`
    ADD CONSTRAINT `travel_log_items_FK` FOREIGN KEY (`place_id`)
        REFERENCES `places` (`id`);

ALTER TABLE `travel_log_items`
    ADD CONSTRAINT `travel_log_items_FK1` FOREIGN KEY (`travel_log_id`)
        REFERENCES `travel_logs` (`id`);


CREATE TABLE `schedules` (
    `id`          BIGINT                                NOT NULL COMMENT '일정_코드',
    `user_id`     BIGINT                                NOT NULL COMMENT '회원_코드',
    `area_id`     BIGINT                                NOT NULL COMMENT '지역_코드',
    `start_date`  DATE                                  NOT NULL COMMENT '여행시작일',
    `end_date`    DATE                                  NOT NULL COMMENT '여행종료일',
    `title`       VARCHAR(255)                          NOT NULL COMMENT '제목',
    `count`       INT COMMENT '참여가능인원',
    `visibility`  VARCHAR(10) DEFAULT 'PUBLIC'          NOT NULL COMMENT '공개상태',
    `is_deleted`  TINYINT     DEFAULT 0                 NOT NULL COMMENT '삭제여부',
    `created_at`  DATETIME    DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `modified_at` DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
    `deleted_at`  DATETIME COMMENT '삭제일시',
    PRIMARY KEY (`id`)
) COMMENT = '일정';

ALTER TABLE `schedules`
    ADD CONSTRAINT `schedules_CK` CHECK ( `visibility` IN ('PUBLIC', 'PRIVATE') );

ALTER TABLE `schedules`
    ADD CONSTRAINT `schedules_FK` FOREIGN KEY (`area_id`)
        REFERENCES `areas` (`id`);

ALTER TABLE `schedules`
    ADD CONSTRAINT `schedules_FK1` FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`);

CREATE TABLE `schedule_items` (
    `id`          BIGINT                             NOT NULL COMMENT '일정계획_코드',
    `schedule_id` BIGINT                             NOT NULL COMMENT '일정_코드',
    `kind`        VARCHAR(10)                        NOT NULL COMMENT '구분',
    `place_id`    BIGINT COMMENT '장소_코드',
    `plan_date`   DATE                               NOT NULL COMMENT '계획일정일자',
    `cost`        INT COMMENT '비용',
    `content`     VARCHAR(500) COMMENT '내용',
    `is_deleted`  TINYINT  DEFAULT 0                 NOT NULL COMMENT '삭제여부',
    `created_at`  DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `modified_at` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
    `deleted_at`  DATETIME COMMENT '삭제일시',
    PRIMARY KEY (`id`)
) COMMENT = '일정항목';

ALTER TABLE `schedule_items`
    ADD CONSTRAINT `schedule_items_CK` CHECK ( `is_deleted` IN (1, 0) );

ALTER TABLE `schedule_items`
    ADD CONSTRAINT `schedule_items_FK` FOREIGN KEY (`place_id`)
        REFERENCES `places` (`id`);

ALTER TABLE `schedule_items`
    ADD CONSTRAINT `schedule_items_FK1` FOREIGN KEY (`place_id`)
        REFERENCES `places` (`id`);

ALTER TABLE `schedule_items`
    ADD CONSTRAINT `schedule_items_FK2` FOREIGN KEY (`place_id`)
        REFERENCES `places` (`id`);

ALTER TABLE `schedule_items`
    ADD CONSTRAINT `schedule_items_FK3` FOREIGN KEY (`schedule_id`)
        REFERENCES `schedules` (`id`);

ALTER TABLE `schedule_items`
    ADD CONSTRAINT `schedule_items_CK1` CHECK ( `kind` IN ('ACCOMMODATION', 'TOURISM', 'TRANSPORTATION', 'ETC') );


CREATE TABLE `schedule_participants` (
    `id`             BIGINT                                NOT NULL COMMENT '일정참여자_코드',
    `reviewer_id`    BIGINT                                NOT NULL COMMENT '회원_코드',
    `schedule_id`    BIGINT                                NOT NULL COMMENT '일정_코드',
    `review_point`   INT COMMENT '일정후기평점',
    `review_content` VARCHAR(500) COMMENT '일정후기내용',
    `status`         VARCHAR(10) DEFAULT 'REQUESTED'       NOT NULL COMMENT '신청상태',
    `cause`          VARCHAR(255) COMMENT '반려사유',
    `created_at`     DATETIME    DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `processed_at`   DATETIME COMMENT '처리일시',
    PRIMARY KEY (`id`)
) COMMENT = '일정참여자';

ALTER TABLE `schedule_participants`
    ADD CONSTRAINT `schedule_participants_FK` FOREIGN KEY (`schedule_id`)
        REFERENCES `schedules` (`id`);

ALTER TABLE `schedule_participants`
    ADD CONSTRAINT `schedule_participants_FK1` FOREIGN KEY (`reviewer_id`)
        REFERENCES `users` (`id`);

ALTER TABLE `schedule_participants`
    ADD CONSTRAINT `schedule_participants_CK` CHECK ( `status` IN ('REQUESTED', 'APPROVAL', 'REJECTED') );


CREATE TABLE `companions` (
    `id`                 BIGINT                                NOT NULL COMMENT '동행글_코드',
    `user_id`            BIGINT                                NOT NULL COMMENT '회원_코드',
    `schedule_id`        BIGINT                                NOT NULL COMMENT '일정_코드',
    `age_restriction`    VARCHAR(127)                          NOT NULL COMMENT '동행글나이',
    `gender_restriction` VARCHAR(127)                          NOT NULL COMMENT '동행글성별',
    `title`              VARCHAR(255)                          NOT NULL COMMENT '제목',
    `content`            TEXT                                  NOT NULL COMMENT '내용',
    `views`              INT         DEFAULT 0 COMMENT '조회수',
    `status`             VARCHAR(10) DEFAULT 'OPEN'            NOT NULL COMMENT '모집상태',
    `is_deleted`         TINYINT     DEFAULT 0                 NOT NULL COMMENT '삭제여부',
    `created_at`         DATETIME    DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `modified_at`        DATETIME    DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
    `deleted_at`         DATETIME COMMENT '삭제일시',
    PRIMARY KEY (`id`)
) COMMENT = '동행글';

ALTER TABLE `companions`
    ADD CONSTRAINT `companions_FK` FOREIGN KEY (`schedule_id`)
        REFERENCES `schedules` (`id`);

ALTER TABLE `companions`
    ADD CONSTRAINT `companions_FK1` FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`);

ALTER TABLE `companions`
    ADD CONSTRAINT `companions_CK` CHECK ( `status` IN ('OPEN', 'COMPLETED') );


CREATE TABLE `user_reviews` (
    `id`               BIGINT                             NOT NULL COMMENT '회원후기_코드',
    `companion_id`     BIGINT                             NOT NULL COMMENT '동행글_코드',
    `reviewer_id`      BIGINT                             NOT NULL COMMENT '리뷰작성자_코드',
    `reviewed_user_id` BIGINT                             NOT NULL COMMENT '리뷰를받는회원_코드',
    `review_point`     INT                                NOT NULL COMMENT '리뷰점수',
    `created_at`       DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `modified_at`      DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '수정일시',
    `deleted_at`       DATETIME COMMENT '삭제일시',
    PRIMARY KEY (`id`)
) COMMENT = '회원후기';

ALTER TABLE `user_reviews`
    ADD CONSTRAINT `user_reviews_FK` FOREIGN KEY (`companion_id`)
        REFERENCES `companions` (`id`);

ALTER TABLE `user_reviews`
    ADD CONSTRAINT `user_reviews_FK1` FOREIGN KEY (`reviewed_user_id`)
        REFERENCES `users` (`id`);

ALTER TABLE `user_reviews`
    ADD CONSTRAINT `user_reviews_FK2` FOREIGN KEY (`reviewer_id`)
        REFERENCES `users` (`id`);


CREATE TABLE `notifications` (
    `id`         BIGINT                             NOT NULL COMMENT '알림_코드',
    `user_id`    BIGINT                             NOT NULL COMMENT '회원_코드',
    `kind`       VARCHAR(30)                        NOT NULL COMMENT '구분',
    `is_read`    TINYINT  DEFAULT 0                 NOT NULL COMMENT '읽음상태',
    `content`    VARCHAR(500)                       NOT NULL COMMENT '메시지내용',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '발송일시',
    PRIMARY KEY (`id`)
) COMMENT = '알림';

ALTER TABLE `notifications`
    ADD CONSTRAINT `notifications_FK` FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`);

ALTER TABLE `notifications`
    ADD CONSTRAINT `notifications_CK` CHECK ( `kind` IN ('QNA', 'REPORT_COMPLETED', 'LIKE', 'FRIEND_REQUEST', 'FRIEND_APPROVAL', 'SCHEDULE_REQUEST', 'SCHEDULE_APPROVAL', 'SCHEDULE_REJECTED',
                                                         'COMPANION_REQUEST', 'COMPANION_APPROVAL', 'COMPANION_REJECTED', 'SETTLEMENT_APPROVED', 'SETTLEMENT_REJECTED', 'POST_UPDATE_APPROVED',
                                                         'POST_UPDATE_REJECTED') );


CREATE TABLE `profiles` (
    `id`            BIGINT                             NOT NULL COMMENT '프로필_코드',
    `user_id`       BIGINT                             NOT NULL COMMENT '회원_코드',
    `nickname`      VARCHAR(50)                        NOT NULL COMMENT '닉네임',
    `profile_image` VARCHAR(255) COMMENT '프로필이미지',
    `introduction`  VARCHAR(500) COMMENT '자기소개',
    `mbti`          VARCHAR(4) COMMENT 'MBTI',
    `created_at`    DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    `modified_at`   DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
    PRIMARY KEY (`id`)
) COMMENT = '프로필';

ALTER TABLE `profiles`
    ADD CONSTRAINT `profiles_FK` FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`);


CREATE TABLE `inquiries` (
    `id`           BIGINT                             NOT NULL COMMENT '문의_코드',
    `user_id`      BIGINT                             NOT NULL COMMENT '회원_코드',
    `kind`         VARCHAR(10)                        NOT NULL COMMENT '분류',
    `content`      VARCHAR(500)                       NOT NULL COMMENT '문의',
    `reply`        VARCHAR(500) COMMENT '답변',
    `created_at`   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '등록일시',
    `processed_at` DATETIME COMMENT '처리일시',
    PRIMARY KEY (`id`)
) COMMENT = '문의';

ALTER TABLE `inquiries`
    ADD CONSTRAINT `inquiries_CK1` CHECK ( `kind` IN ('INQUIRY', 'SUGGESTION') );

ALTER TABLE `inquiries`
    ADD CONSTRAINT `inquiries_FK` FOREIGN KEY (`user_id`)
        REFERENCES `users` (`id`);


CREATE TABLE `friends` (
    `id`           BIGINT                        NOT NULL COMMENT '친구_코드',
    `requester_id` BIGINT                        NOT NULL COMMENT '회원_코드',
    `accepter_id`  BIGINT                        NOT NULL COMMENT '친구대상회원_코드',
    `status`       VARCHAR(10) DEFAULT 'PENDING' NOT NULL COMMENT '친구상태',
    PRIMARY KEY (`id`)
) COMMENT = '친구';

ALTER TABLE `friends`
    ADD CONSTRAINT `friends_FK` FOREIGN KEY (`requester_id`)
        REFERENCES `users` (`id`);

ALTER TABLE `friends`
    ADD CONSTRAINT `friends_FK1` FOREIGN KEY (`accepter_id`)
        REFERENCES `users` (`id`);


CREATE TABLE `blocks` (
    `id`           BIGINT                             NOT NULL COMMENT '차단_코드',
    `blocked_id`   BIGINT                             NOT NULL COMMENT '차단된회원_코드',
    `blocker_id`   BIGINT                             NOT NULL COMMENT '차단한회원_코드',
    `status`       VARCHAR(10)                        NOT NULL COMMENT '상태',
    `blocked_at`   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '차단일시',
    `modified_at`  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',
    `unblocked_at` DATETIME COMMENT '차단해제일시',
    PRIMARY KEY (`id`)
) COMMENT = '차단';

ALTER TABLE `blocks`
    ADD CONSTRAINT `blocks_FK` FOREIGN KEY (`blocker_id`)
        REFERENCES `users` (`id`);

ALTER TABLE `blocks`
    ADD CONSTRAINT `blocks_FK1` FOREIGN KEY (`blocked_id`)
        REFERENCES `users` (`id`);

CREATE TABLE `likes` (
    `id`           BIGINT NOT NULL COMMENT '좋아요_코드',
    `user_id`      BIGINT NOT NULL COMMENT '회원_코드',
    `kind`         VARCHAR(20) COMMENT '분류',
    `schedule_id`  BIGINT COMMENT '일정_코드',
    `travel_id`    BIGINT NOT NULL COMMENT '여행지소개_코드',
    `companion_id` BIGINT COMMENT '동행글_코드',
    PRIMARY KEY (`id`)
) COMMENT = '좋아요';

ALTER TABLE `likes`
    ADD CONSTRAINT `likes_FK` FOREIGN KEY (`companion_id`)
        REFERENCES `companions` (`id`);

ALTER TABLE `likes`
    ADD CONSTRAINT `likes_FK1` FOREIGN KEY (`schedule_id`)
        REFERENCES `schedules` (`id`);

ALTER TABLE `likes`
    ADD CONSTRAINT `likes_FK2` FOREIGN KEY (`travel_id`)
        REFERENCES `travels` (`id`);

ALTER TABLE `likes`
    ADD CONSTRAINT `likes_CK` CHECK ( `kind` IN ('SCHEDULE', 'TRAVEL', 'COMPANION') );


CREATE TABLE `reports` (
    `id`           BIGINT                                NOT NULL COMMENT '신고_코드',
    `reporter_id`  BIGINT                                NOT NULL COMMENT '신고자_코드',
    `kind`         VARCHAR(20)                           NOT NULL COMMENT '분류',
    `schedule_id`  BIGINT COMMENT '일정_코드',
    `reportee_id`  BIGINT COMMENT '신고대상회원_코드',
    `travel_id`    BIGINT COMMENT '여행지소개_코드',
    `companion_id` BIGINT COMMENT '동행글_코드',
    `type`         VARCHAR(20)                           NOT NULL COMMENT '신고타입',
    `content`      TEXT                                  NOT NULL COMMENT '신고내용',
    `status`       VARCHAR(10) DEFAULT 'REQUESTED'       NOT NULL COMMENT '처리상태',
    `reported_at`  DATETIME    DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '신고일시',
    `processed_at` DATETIME COMMENT '처리일시',
    PRIMARY KEY (`id`)
) COMMENT = '신고';

ALTER TABLE `reports`
    ADD CONSTRAINT `reports_CK2` CHECK ( `status` IN ('REQUESTED', 'COMPLETED') );

ALTER TABLE `reports`
    ADD CONSTRAINT `reports_CK` CHECK ( `kind` IN ('USER', 'COMPANION', 'SCHEDULE', 'TRAVEL') );

ALTER TABLE `reports`
    ADD CONSTRAINT `reports_CK1` CHECK ( `type` IN ('PROFANITY', 'ADVERTISING', 'HARASSMENT', 'SEXUAL_HARASSMENT') );

ALTER TABLE `reports`
    ADD CONSTRAINT `reports_FK` FOREIGN KEY (`companion_id`)
        REFERENCES `companions` (`id`);

ALTER TABLE `reports`
    ADD CONSTRAINT `reports_FK1` FOREIGN KEY (`schedule_id`)
        REFERENCES `schedules` (`id`);

ALTER TABLE `reports`
    ADD CONSTRAINT `reports_FK2` FOREIGN KEY (`travel_id`)
        REFERENCES `travels` (`id`);

ALTER TABLE `reports`
    ADD CONSTRAINT `reports_FK4` FOREIGN KEY (`reportee_id`)
        REFERENCES `users` (`id`);


CREATE TABLE `images` (
    `id`           BIGINT                             NOT NULL COMMENT '이미지_코드',
    `travel_id`    BIGINT COMMENT '여행지소개_코드',
    `schedule_id`  BIGINT COMMENT '일정_코드',
    `companion_id` BIGINT COMMENT '동행글_코드',
    `report_id`    BIGINT COMMENT '신고_코드',
    `inquiry_id`   BIGINT COMMENT '문의_코드',
    `kind`         VARCHAR(20)                        NOT NULL COMMENT '분류',
    `path`         VARCHAR(255)                       NOT NULL COMMENT '경로',
    `uuid`         VARCHAR(255)                       NOT NULL COMMENT '식별파일명',
    `name`         VARCHAR(255)                       NOT NULL COMMENT '원본파일명',
    `extension`    VARCHAR(127)                       NOT NULL COMMENT '확장자',
    `created_at`   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '생성일시',
    PRIMARY KEY (`id`)
) COMMENT = '이미지';

ALTER TABLE `images`
    ADD CONSTRAINT `images_FK2` FOREIGN KEY (`report_id`)
        REFERENCES `reports` (`id`);

ALTER TABLE `images`
    ADD CONSTRAINT `images_CK` CHECK ( `kind` IN ('TRAVEL', 'SCHEDULE', 'COMPANION', 'REPORT', 'INQUIRY') );

ALTER TABLE `images`
    ADD CONSTRAINT `images_FK` FOREIGN KEY (`companion_id`)
        REFERENCES `companions` (`id`);

ALTER TABLE `images`
    ADD CONSTRAINT `images_FK1` FOREIGN KEY (`inquiry_id`)
        REFERENCES `inquiries` (`id`);

ALTER TABLE `images`
    ADD CONSTRAINT `images_FK3` FOREIGN KEY (`schedule_id`)
        REFERENCES `schedules` (`id`);

ALTER TABLE `images`
    ADD CONSTRAINT `images_FK4` FOREIGN KEY (`travel_id`)
        REFERENCES `travels` (`id`);


