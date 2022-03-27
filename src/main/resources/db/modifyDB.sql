ALTER TABLE users
    RENAME COLUMN nickname
        TO username;

alter table users alter column password type varchar(100) using password::varchar(255);

alter table users rename column enabled to status;

alter table users alter column status type varchar(25) using status::varchar(25);
