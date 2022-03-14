/*DELETE
FROM users;

INSERT INTO users (nickname, first_name, last_name, email, password, role_id, enabled)
VALUES ('paul', 'Paul', 'Musienko', 'paul77@ukr.net', 'psw', 1, 1),
       ('nick', 'Nick', 'Malkovich', 'nm@gmail.com', 'psw1', 2, 1);

*/
DELETE
FROM posts;

/*DELETE
FROM posts;

INSERT INTO posts (author_id, post_title, post_content, post_created_date, post_modified_date, post_category_id, post_status_id)
VALUES (1, 'test1', 'content1', 2022-01-11, 2022-01-11,1,1);*/

INSERT INTO roles (role_name)
VALUES
       ('ADMIN'),
       ('USER'),
       ('MODERATOR');