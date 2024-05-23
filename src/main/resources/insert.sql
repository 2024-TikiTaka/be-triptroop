/* 회원 테이블 */
/* 관리자(admin), 회원(1234) 데이터 */
INSERT INTO triptroop.users (id, email, password, name, birth, role, gender, phone, godo, status, is_matched, created_at, modified_at, deleted_at)
VALUES (1, 'triptroop.official@gmail.com', '$2y$10$/IbguO6L8dUmjtk2DdwLruvaAA/A.3gcSbos02ZqNjxAaEtXLZvPa', '관리자', '1990-01-01', 'ADMIN', NULL, NULL, 38, 'ACTIVE', 1, '2024-05-23 11:13:58',
        '2024-05-23 11:15:24', NULL);
INSERT INTO triptroop.users (id, email, password, name, birth, role, gender, phone, godo, status, is_matched, created_at, modified_at, deleted_at)
VALUES (2, 'user1@email.com', '$2y$10$Zs87.oPTTWaIga6P1VNNne4bGwStOYjoHyckRSlN4Ng/ZZ7goeJUS', '김회원', '1996-01-01', 'USER', NULL, NULL, 38, 'ACTIVE', 1, '2024-05-23 11:15:00', '2024-05-23 11:15:00',
        NULL);
INSERT INTO triptroop.users (id, email, password, name, birth, role, gender, phone, godo, status, is_matched, created_at, modified_at, deleted_at)
VALUES (3, 'user2@email.com', '$2y$10$Zs87.oPTTWaIga6P1VNNne4bGwStOYjoHyckRSlN4Ng/ZZ7goeJUS', '박회원', '1997-01-01', 'USER', NULL, NULL, 38, 'ACTIVE', 1, '2024-05-23 11:15:00', '2024-05-23 11:15:00',
        NULL);
INSERT INTO triptroop.users (id, email, password, name, birth, role, gender, phone, godo, status, is_matched, created_at, modified_at, deleted_at)
VALUES (4, 'user3@email.com', '$2y$10$Zs87.oPTTWaIga6P1VNNne4bGwStOYjoHyckRSlN4Ng/ZZ7goeJUS', '이회원', '1998-01-01', 'USER', NULL, NULL, 38, 'ACTIVE', 1, '2024-05-23 11:15:00', '2024-05-23 11:15:00',
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