INSERT INTO roles
VALUES(1, 'ROLE_ADMIN');

INSERT INTO roles
VALUES(2, 'ROLE_USER');

INSERT INTO users (user_id, fullname, username, password)
VALUES('101', 'Rezaur Rahman', 'DevRezaur', 'iamadmin');

INSERT INTO users (user_id, fullname, username, password)
VALUES('102', 'Sanzida Sultana', 'SanzidaSultana', 'iamuser');

INSERT INTO user_role
VALUES('101', 1);

INSERT INTO user_role
VALUES('101', 2);

INSERT INTO user_role
VALUES('102', 2);