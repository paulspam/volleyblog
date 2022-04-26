/*DELETE
FROM users;

INSERT INTO users (nickname, first_name, last_name, email, password, role_id, enabled)
VALUES ('paul', 'Paul', 'Musienko', 'paul77@ukr.net', 'psw', 1, 1),
       ('nick', 'Nick', 'Malkovich', 'nm@gmail.com', 'psw1', 2, 1);

*/
DELETE
FROM posts;

DELETE
FROM posts;

INSERT INTO post_statuses (status_name)
VALUES
      ('PUBLISHED'),
      ('MODERATION'),
      ('REJECTED');

INSERT INTO post_categories (category_name)
VALUES
      ('Новини'),
      ('Статті'),
      ('Чемпіонат України'),
      ('Чемпіонат Європи'),
      ('Чемпіонат Світу'),
      ('Команди');

INSERT INTO posts (author_id, post_title, post_content, post_created_date, post_modified_date, post_category_id, post_status_id)
VALUES (1, 'test1', 'content1', '2022-01-11', '2022-01-11',1,1);

INSERT INTO post_likes (post_id, like_value, user_id)
VALUES
       (3, 1, 1),
       (6, 1, 1),
       (5, 1, 1),
       (3, 1, 2),
       (6, 1, 2),
       (3, 1, 3);

INSERT INTO roles (role_name)
VALUES
       ('ADMIN'),
       ('USER'),
       ('MODERATOR');

INSERT INTO permissions (name)
VALUES
('READ_AMPLUA'),
('MANAGE_AMPLUA'),
('READ_COMMENT'),
('MANAGE_COMMENTS'),
('READ_LIKE'),
('MANAGE_LIKES'),
('READ_PERMITION'),
('MANAGE_PERMITIONS'),
('READ_PLAYER'),
('MANAGE_PLAYERS'),
('READ_POST'),
('MANAGE_POSTS'),
('READ_POST_CATEGORY'),
('MANAGE_POST_CATEGORIES'),
('READ_POST_STATUS'),
('MANAGE_POST_STATUSES'),
('READ_ROLE'),
('MANAGE_ROLES'),
('READ_TAG'),
('MANAGE_TAGS'),
('READ_TEAM'),
('MANAGE_TEAMS'),
('READ_USER'),
('MANAGE_USERS');

-- ADMIN Permissions
INSERT INTO role_permissions (role_id, permission_id)
VALUES
(1, 2),
(1, 4),
(1, 6),
(1, 8),
(1, 10),
(1, 12),
(1, 14),
(1, 16),
(1, 18),
(1, 20),
(1, 22),
(1, 24);

-- ADMIN additional permissions
INSERT INTO role_permissions (role_id, permission_id)
VALUES
       (1, 7),
       (1, 13),
       (1, 15),
       (1, 17),
       (1, 23);

-- USER Permissions
INSERT INTO role_permissions (role_id, permission_id)
VALUES
(2, 1),
(2, 4),
(2, 6),
(2, 7),
(2, 10),
(2, 12),
(2, 13),
(2, 15),
(2, 17),
(2, 20),
(2, 22),
(2, 24);




INSERT INTO permissions (name)
VALUES
('READ_AMPLUA'),
('MANAGE_AMPLUA'),
('READ_COMMENT'),
('MANAGE_COMMENTS'),
('READ_LIKE'),
('MANAGE_LIKES'),
('READ_PERMITION'),
('MANAGE_PERMITIONS'),
('READ_PLAYER'),
('MANAGE_PLAYERS'),
('READ_POST'),
('MANAGE_POSTS'),
('READ_POST_CATEGORY'),
('MANAGE_POST_CATEGORIES'),
('READ_POST_STATUS'),
('MANAGE_POST_STATUSES'),
('READ_ROLE'),
('MANAGE_ROLES'),
('READ_TAG'),
('MANAGE_TAGS'),
('READ_TEAM'),
('MANAGE_TEAMS'),
('READ_USER'),
('MANAGE_USERS');

-- ADMIN Permissions
INSERT INTO role_permissions (role_id, permission_id)
VALUES
(1, 2),
(1, 4),
(1, 6),
(1, 8),
(1, 10),
(1, 12),
(1, 14),
(1, 16),
(1, 18),
(1, 20),
(1, 22),
(1, 24);

-- USER Permissions
INSERT INTO role_permissions (role_id, permission_id)
VALUES
(2, 1),
(2, 4),
(2, 6),
(2, 7),
(2, 10),
(2, 12),
(2, 13),
(2, 15),
(2, 17),
(2, 20),
(2, 22),
(2, 24);

INSERT INTO amplua (amplua_name)
VALUES
('связующий'),
('диагональный'),
('центральный блокирующий'),
('доигровщик'),
('либеро');

INSERT INTO teams (team_name, city, team_rating)
VALUES
       ('Локомотив', 'Харків', 50),
       ('Барком-Кажани', 'Львів', 48),
       ('Хімпром', 'Суми', 24),
       ('Дніпро', 'Дніпрол', 20),
       ('Юракадемія', 'Харків', 18);

INSERT INTO players (user_id, nickname, team_id, amplua_id, player_rating)
VALUES
       (1, 'player1', 1, 1, 3),
       (2, 'player2', 2, 2, 5),
       (3, 'player3', 1, 4, 2),
       (4, 'player4', 3, 1, 8),
       (5, 'player5', 1, 2, 7),
       (6, 'player1', 4, 1, 10);

INSERT INTO tags (name)
VALUES
('Україна'),
('Чемпіонат міста'),
('Локомотив Харків'),
('Тег1'),
('Тег2'),
('Тег3');

INSERT INTO post_tags (post_id, tag_id)
VALUES
(4, 1),
(4, 3),
(5, 1),
(5, 2),
(10, 4),
(13, 4),
(13, 5),
(13, 6);




