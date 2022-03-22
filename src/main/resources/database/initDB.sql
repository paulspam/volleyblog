DROP TABLE IF EXISTS comment_likes;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS post_likes;
DROP TABLE IF EXISTS posts;
DROP TABLE IF EXISTS post_categories;
DROP TABLE IF EXISTS post_statuses;
DROP TABLE IF EXISTS players;
DROP TABLE IF EXISTS amplua;
DROP TABLE IF EXISTS teams;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS role_permissions;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS permissions;
DROP TABLE IF EXISTS humans;

CREATE TABLE humans
(
    human_id   SERIAL PRIMARY KEY,
    first_name VARCHAR(20),
    last_name  VARCHAR(25),
    gender     SMALLINT
);

CREATE TABLE roles
(
    role_id   SERIAL PRIMARY KEY,
    role_name VARCHAR(20)
);

create table permissions
(
    id   serial
        constraint permissions_pk
            primary key,
    name varchar(30) not null
);

create unique index permissions_name_uindex
    on permissions (name);

create table role_permissions
(
    role_id       int not null
        constraint role_fk
            references roles,
    permission_id int not null
        constraint permissions_fk
            references permissions,
    constraint role_permissions_pk
        primary key (role_id, permission_id)
);


CREATE TABLE users
(
    user_id    SERIAL PRIMARY KEY,
    username   VARCHAR(15),
    password   VARCHAR(100),
    email      VARCHAR(30),
    human_id INT,
    role_id    INT,
    enabled    SMALLINT,
    FOREIGN KEY (role_id) REFERENCES roles (role_id)
);
create unique index users_username_uindex
    on users (username);
create unique index users_email_uindex
    on users (email);


CREATE TABLE amplua
(
    amplua_id   SERIAL PRIMARY KEY,
    amplua_name VARCHAR(30)
);
create unique index amplua_amplua_name_uindex
    on amplua (amplua_name);

CREATE TABLE teams
(
    team_id     SERIAL PRIMARY KEY,
    team_name   VARCHAR(30),
    city        VARCHAR(30),
    team_rating INT
);

CREATE TABLE players
(
    player_id     SERIAL PRIMARY KEY,
    user_id       SERIAL,
    nickname      VARCHAR(25),
    team_id       SERIAL,
    amplua_id     SERIAL,
    player_rating INTEGER,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (team_id) REFERENCES teams (team_id),
    FOREIGN KEY (amplua_id) REFERENCES amplua (amplua_id)
);

CREATE TABLE post_categories
(
    category_id   SERIAL PRIMARY KEY,
    category_name VARCHAR(30)
);

CREATE TABLE post_statuses
(
    status_id   SERIAL PRIMARY KEY,
    status_name VARCHAR(30)
);

CREATE TABLE posts
(
    post_id            SERIAL PRIMARY KEY,
    author_id          INT,
    post_title         VARCHAR(50),
    post_content       TEXT,
    post_created_date  DATE,
    post_modified_date DATE,
    post_category_id   INT,
    post_status_id     INT,
    FOREIGN KEY (author_id) REFERENCES users (user_id),
    FOREIGN KEY (post_category_id) REFERENCES post_categories (category_id),
    FOREIGN KEY (post_status_id) REFERENCES post_statuses (status_id)
);

CREATE TABLE post_likes
(
    like_id    SERIAL PRIMARY KEY,
    post_id    INT,
    like_value SMALLINT,
    FOREIGN KEY (post_id) REFERENCES posts (post_id)
);

CREATE TABLE comments
(
    comment_id            SERIAL PRIMARY KEY,
    post_id               INT,
    author_id             INT,
    comment_content       TEXT,
    comment_created_date  DATE,
    comment_modified_date DATE,
    FOREIGN KEY (post_id) REFERENCES posts (post_id),
    FOREIGN KEY (author_id) REFERENCES users (user_id)
);

CREATE TABLE comment_likes
(
    like_id    SERIAL PRIMARY KEY,
    comment_id INT,
    like_value SMALLINT,
    FOREIGN KEY (comment_id) REFERENCES comments (comment_id)
);