/* 회원 테이블 */
/* 관리자(admin), 회원(1234) 데이터 */
INSERT INTO triptroop.users (id, email, password, name, birth, role, gender, phone, godo, status, is_matched,
                             created_at, modified_at, deleted_at)
VALUES (1, 'triptroop.official@gmail.com', '$2y$10$/IbguO6L8dUmjtk2DdwLruvaAA/A.3gcSbos02ZqNjxAaEtXLZvPa', '관리자',
        '1990-01-01', 'ADMIN', NULL, NULL, 38, 'ACTIVE', 1, '2024-05-23 11:13:58',
        '2024-05-23 11:15:24', NULL);
INSERT INTO triptroop.users (id, email, password, name, birth, role, gender, phone, godo, status, is_matched,
                             created_at, modified_at, deleted_at)
VALUES (2, 'user1@email.com', '$2y$10$Zs87.oPTTWaIga6P1VNNne4bGwStOYjoHyckRSlN4Ng/ZZ7goeJUS', '김회원', '1996-01-01',
        'USER', NULL, NULL, 38, 'ACTIVE', 1, '2024-05-23 11:15:00', '2024-05-23 11:15:00',
        NULL);
INSERT INTO triptroop.users (id, email, password, name, birth, role, gender, phone, godo, status, is_matched,
                             created_at, modified_at, deleted_at)
VALUES (3, 'user2@email.com', '$2y$10$Zs87.oPTTWaIga6P1VNNne4bGwStOYjoHyckRSlN4Ng/ZZ7goeJUS', '박회원', '1997-01-01',
        'USER', NULL, NULL, 38, 'ACTIVE', 1, '2024-05-23 11:15:00', '2024-05-23 11:15:00',
        NULL);
INSERT INTO triptroop.users (id, email, password, name, birth, role, gender, phone, godo, status, is_matched,
                             created_at, modified_at, deleted_at)
VALUES (4, 'user3@email.com', '$2y$10$Zs87.oPTTWaIga6P1VNNne4bGwStOYjoHyckRSlN4Ng/ZZ7goeJUS', '이회원', '1998-01-01',
        'USER', NULL, NULL, 38, 'ACTIVE', 1, '2024-05-23 11:15:00', '2024-05-23 11:15:00',
        NULL);

/* 지역 테이블 */
INSERT INTO triptroop.areas (id, sido)
VALUES (1, '서울'),
       (2, '부산'),
       (3, '대구'),
       (4, '인천'),
       (5, '광주'),
       (6, '대전'),
       (7, '울산'),
       (8, '세종'),
       (9, '경기'),
       (10, '강원');

/* 카테고리 테이블 */
INSERT INTO triptroop.categories (id, name)
VALUES (1, '관광지'),
       (2, '문화시설'),
       (3, '축제'),
       (4, '행사'),
       (5, '레포츠'),
       (6, '숙박'),
       (7, '쇼핑'),
       (8, '음식점'),
       (9, '여행코스');

INSERT INTO triptroop.places (id, name)
VALUES (1, '경복궁', 1),
       (2, '해운대', 2),
       (3, '팔공산', 3),
       (4, '인천 차이나타운', 4),
       (5, '광주 무등산', 5),
       (6, '대전 엑스포', 6),
       (7, '울산 대왕암', 7),
       (8, '세종 호수공원', 8),
       (9, '에버랜드', 9),
       (10, '설악산', 10);

INSERT INTO triptroop.travels (user_id, category_id, area_id, place_id, title, content, visibility, is_deleted,
                               created_at, modified_at)
VALUES (1, 1, 1, 1, '경복궁 여행', '경복궁은 한국의 대표적인 궁궐입니다.', 'PUBLIC', 0, NOW(), NOW()),
       (2, 2, 2, 2, '해운대 해변', '해운대는 부산의 유명한 해변입니다.', 'PRIVATE', 0, NOW(), NOW()),
       (3, 3, 3, 3, '팔공산 등반', '팔공산은 대구의 대표적인 산입니다.', 'PUBLIC', 0, NOW(), NOW()),
       (1, 4, 4, 4, '인천 차이나타운 탐방', '인천 차이나타운은 다양한 중국 문화를 체험할 수 있습니다.', 'PUBLIC', 0, NOW(), NOW()),
       (2, 5, 5, 5, '광주 무등산 등산', '무등산은 광주의 아름다운 산입니다.', 'PRIVATE', 0, NOW(), NOW());


