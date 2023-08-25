/*
    CLOSETALK 개발용 초기 데이터

======================================================================
   구분  | 작성자 | 작성내용                                 |  작성일
----------------------------------------------------------------------
 최초작성 | 김민정 | 사용 방법 및 USER 테이블 초기 데이터 작성    | 23.08.18


* 사용방법
    : SQL을 복사하여 직접 DB에 삽입하거나 YAML 설정을 마친 후 프로젝트 실행

* YAML 설정하기
    1. YAML에 아래 설정을 추가한다.
----------------------------------
spring:
  jpa:
    defer-datasource-initialization: true
  sql:
    init:
      mode: always
----------------------------------
    2. YAML에서 DDL-AUTO 설정을 'CREATE' 또는 'CREATE-DROP'으로
    3. data.sql에서 로컬에 ENTITY가 존재하지 않는 테이블의 INSERT문은 주석처리 한다.
    4. 로컬에서 프로젝트 실행을 하고 기존에 사용하던 DDL-AUTO설정이 'UPDATE'였다면 다시 'UPDATE'로 변경하고
        위에 추가했던 설정은 주석처리한다.


*/


-- INSERT USERS
INSERT INTO users (id, login_id, password,  nickname, email, profile_image_url, created_at)
VALUES (1,'user01', '', 'userNo.1', 'user01@closetalk.com', '/src/main/resources/static/images/profile/default_profile.png', '2023-08-17 17:54:33');
INSERT INTO users (id, login_id, password,  nickname, email, profile_image_url, created_at)
VALUES (2,'user02', '', 'NextUser', 'user02@closetalk.com', '/src/main/resources/static/images/profile/default_profile.png', '2023-08-17 17:13:44');
INSERT INTO users (id, login_id, password,  nickname, email, profile_image_url, created_at)
VALUES (3,'user03', '', '블랙맘바를삼켜', 'user03@closetalk.com', '/src/main/resources/static/images/profile/default_profile.png', '2023-08-17 22:10:06');
INSERT INTO users (id, login_id, password,  nickname, email, profile_image_url, created_at)
VALUES (4,'user04', '', '힘내어떡해이겨내', 'user04@closetalk.com', '/src/main/resources/static/images/profile/default_profile.png', '2023-08-18 00:11:03');
INSERT INTO users (id, login_id, password,  nickname, email, profile_image_url, created_at)
VALUES (5,'user05', '', '우주최강햄스터', 'user05@closetalk.com', '/src/main/resources/static/images/profile/default_profile.png', '2023-08-18 03:11:22');

-- INSERT CLOSET
INSERT INTO closet (closet_name, is_hidden, users_id) VALUES ('My closet 1', false, (SELECT id FROM users LIMIT 1 OFFSET 0));
INSERT INTO closet (closet_name, is_hidden, users_id) VALUES ('아우터 모음', true, (SELECT id FROM users LIMIT 1 OFFSET 0));

-- INSERT CLOSET_ITEM
INSERT INTO closet_item (brand, category, item_image_url, item_name, price, description, created_at, closet_id)
VALUES ('나이키', 'TOP', '/nike1.png', '나이키 반팔', 29800, '운동용', NOW(), (SELECT id FROM closet LIMIT 1 OFFSET 0));
INSERT INTO closet_item (brand, category, item_image_url, item_name, price, description, created_at, modified_at, closet_id)
VALUES ('아디다스', 'OUTER', '/adi_ter1.png', '아디다스 패딩', 324207, '생존용 롱패딩 구비', '2023-08-15 13:24:12', NOW(), (SELECT id FROM closet LIMIT 1 OFFSET 1));
INSERT INTO closet_item (brand, category, item_image_url, item_name, price, description, created_at, modified_at, closet_id)
VALUES ('보세', 'OUTER', '/dlalwl2_zz.png', '흑청자켓', 77000, '', '2023-08-16 11:23:32', NOW(), (SELECT id FROM closet LIMIT 1 OFFSET 1));

-- INSERT OOTD_ARTICLE
insert into ootd_article (id, content, thumbnail) VALUES (1, '첫번째 글', '/static/ootd/images/1/1.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (2, '두번째 글', '/static/ootd/images/2/2.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (3, '세번째 글', '/static/ootd/images/3/3.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (4, '네번째 글', '/static/ootd/images/4/4.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (5, '다섯번째 글', '/static/ootd/images/5/5.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (6, '여섯번째 글', '/static/ootd/images/6/6.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (7, '일곱번째 글', '/static/ootd/images/7/7.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (8, '여덟번째 글', '/static/ootd/images/8/8.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (9, '아홉번째 글', '/static/ootd/images/9/9.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (10, '열번째 글', '/static/ootd/images/10/10.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (11, '열한번째 글', '/static/ootd/images/11/11.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (12, '열두번째 글', '/static/ootd/images/12/12.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (13, 'test', '/static/ootd/images/13/13.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (14, 'test', '/static/ootd/images/14/14.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (15, 'test 글', '/static/ootd/images/15/15.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (16, 'test 글', '/static/ootd/images/16/16.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (17, 'test 글', '/static/ootd/images/17/17.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (18, 'test 글', '/static/ootd/images/18/18.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (19, 'test 글', '/static/ootd/images/19/19.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (20, 'test 글', '/static/ootd/images/20/20.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (21, 'test 글', '/static/ootd/images/21/21.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (22, 'test 글', '/static/ootd/images/22/22.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (23, 'test 글', '/static/ootd/images/23/23.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (24, 'test 글', '/static/ootd/images/24/24.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (25, 'test 글', '/static/ootd/images/25/25.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (26, 'test 글', '/static/ootd/images/26/26.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (27, 'test 글', '/static/ootd/images/27/27.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (28, 'test 글', '/static/ootd/images/28/28.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (29, 'test 글', '/static/ootd/images/29/29.jpg');
insert into ootd_article (id, content, thumbnail) VALUES (30, 'test 글', '/static/ootd/images/30/30.jpg');

-- INSERT OOTD_ARTICLE_IMAGES
insert into ootd_article_images (id, ootd_article_id, image_url) values (1, 1, '/static/ootd/images/1/1.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (2, 2, '/static/ootd/images/2/2.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (3, 3, '/static/ootd/images/3/3.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (4, 4, '/static/ootd/images/4/4.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (5, 5, '/static/ootd/images/5/5.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (6, 6, '/static/ootd/images/6/6.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (7, 7, '/static/ootd/images/7/7.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (8, 8, '/static/ootd/images/8/8.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (9, 9, '/static/ootd/images/9/9.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (10, 10, '/static/ootd/images/10/10.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (11, 11, '/static/ootd/images/11/11.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (12, 12, '/static/ootd/images/12/12.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (13, 13, '/static/ootd/images/13/13.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (14, 14, '/static/ootd/images/14/14.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (15, 15, '/static/ootd/images/15/15.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (16, 16, '/static/ootd/images/16/16.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (17, 17, '/static/ootd/images/17/17.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (18, 18, '/static/ootd/images/18/18.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (19, 19, '/static/ootd/images/19/19.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (20, 20, '/static/ootd/images/20/20.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (21, 21, '/static/ootd/images/21/21.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (22, 22, '/static/ootd/images/22/22.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (23, 23, '/static/ootd/images/23/23.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (24, 24, '/static/ootd/images/24/24.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (25, 25, '/static/ootd/images/25/25.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (26, 26, '/static/ootd/images/26/26.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (27, 27, '/static/ootd/images/27/27.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (28, 28, '/static/ootd/images/28/28.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (29, 29, '/static/ootd/images/29/29.jpg');
insert into ootd_article_images (id, ootd_article_id, image_url) values (30, 30, '/static/ootd/images/30/30.jpg');

-- INSERT OOTD_COMMENT
-- INSERT OOTD_LIKE..?

-- INSERT ISSUE_ARTICLE
insert into issue_article (id, content, thumbnail) VALUES (1, '첫번째 글', '/static/issue/1/1.jpg');
insert into issue_article (id, content, thumbnail) VALUES (2, '두번째 글', '/static/issue/2/2.jpg');
insert into issue_article (id, content, thumbnail) VALUES (3, '세번째 글', '/static/issue/3/3.jpg');
insert into issue_article (id, content, thumbnail) VALUES (4, '네번째 글', '/static/issue/4/1.jpg');
insert into issue_article (id, content, thumbnail) VALUES (5, '다섯번째 글', '/static/issue/5/2.jpg');
insert into issue_article (id, content, thumbnail) VALUES (6, '여섯번째 글', '/static/issue/6/3.jpg');

-- INSERT ISSUE_ARTICLE_IMAGES
insert into issue_article_images (id, issue_article_id, image_url) values (1, 1, '/static/issue/1/1.jpg');
insert into issue_article_images (id, issue_article_id, image_url) values (2, 2, '/static/issue/2/2.jpg');
insert into issue_article_images (id, issue_article_id, image_url) values (3, 3, '/static/issue/3/3.jpg');
insert into issue_article_images (id, issue_article_id, image_url) values (4, 4, '/static/issue/4/1.jpg');
insert into issue_article_images (id, issue_article_id, image_url) values (5, 5, '/static/issue/5/2.jpg');
insert into issue_article_images (id, issue_article_id, image_url) values (6, 6, '/static/issue/6/3.jpg');

-- INSERT COMMUNITY_ARTICLE
insert into community_article (id, category, title, content, thumbnail, created_at) values (1, 'test', '예시 1번', 'test', '/static/community/1/1', '2023-08-24');
insert into community_article (id, category, title, content, thumbnail, created_at) values (2, 'test', '예시 2번', 'test', '/static/community/2/4', '2023-08-24');
insert into community_article (id, category, title, content, thumbnail, created_at) values (3, 'test', '예시 3번', 'test', '/static/community/3/7', '2023-08-24');
insert into community_article (id, category, title, content, thumbnail, created_at) values (4, 'test', '예시 4번', 'test', '/static/community/4/10', '2023-08-24');
insert into community_article (id, category, title, content, thumbnail, created_at) values (5, 'test', '예시 5번', 'test', '/static/community/5/13', '2023-08-24');

-- INSERT COMMUNITY_ARTICLE_IMAGES
insert into community_article_images (id, community_article_id, image_url) values (1, 1, '/static/community/1/1');
insert into community_article_images (id, community_article_id, image_url) values (2, 1, '/static/community/1/2');
insert into community_article_images (id, community_article_id, image_url) values (3, 1, '/static/community/1/3');
insert into community_article_images (id, community_article_id, image_url) values (4, 2, '/static/community/2/4');
insert into community_article_images (id, community_article_id, image_url) values (5, 2, '/static/community/2/5');
insert into community_article_images (id, community_article_id, image_url) values (6, 2, '/static/community/2/6');
insert into community_article_images (id, community_article_id, image_url) values (7, 3, '/static/community/3/7');
insert into community_article_images (id, community_article_id, image_url) values (8, 3, '/static/community/3/8');
insert into community_article_images (id, community_article_id, image_url) values (9, 3, '/static/community/3/9');
insert into community_article_images (id, community_article_id, image_url) values (10, 4, '/static/community/4/10');
insert into community_article_images (id, community_article_id, image_url) values (11, 4, '/static/community/4/11');
insert into community_article_images (id, community_article_id, image_url) values (12, 4, '/static/community/4/12');
insert into community_article_images (id, community_article_id, image_url) values (13, 5, '/static/community/5/13');
insert into community_article_images (id, community_article_id, image_url) values (14, 5, '/static/community/5/14');
insert into community_article_images (id, community_article_id, image_url) values (15, 5, '/static/community/5/15');

-- INSERT COMMUNITY_COMMENT
insert into community_comment (id, community_article_id, content, created_at) values  (1, 1, 'bdns', '2023-08-24');
insert into community_comment (id, community_article_id, content, created_at) values  (2, 1, '오~씻', '2023-08-24');
insert into community_comment (id, community_article_id, parent_comment_id, content, created_at) values  (3, 1, 1, 'presents', '2023-08-24');

-- INSERT COMMUNITY_LIKE
-- INSERT COMMUNITY_CLOSET_ITEM