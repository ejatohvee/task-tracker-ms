INSERT INTO users_management.t_users (id, c_username, c_email, c_password) VALUES (1, 'Maks', 'maks@', '1234343');
INSERT INTO users_management.t_users (id, c_username, c_email, c_password) VALUES (2, 'Mark', 'mark@', '1234343');
INSERT INTO users_management.t_users (id, c_username, c_email, c_password) VALUES (3, 'Tim', 'tim@', '1234343');

INSERT INTO users_management.t_authorities (id, c_authority_type) VALUES (1, 'ROLE_ADMIN');
INSERT INTO users_management.t_authorities (id, c_authority_type) VALUES (2, 'ROLE_USER');

INSERT INTO users_management.user_authorities (id, user_id, authority_id) VALUES (1, 1, 1);
INSERT INTO users_management.user_authorities (id, user_id, authority_id) VALUES (2, 1, 2);
INSERT INTO users_management.user_authorities (id, user_id, authority_id) VALUES (3, 2, 2);

INSERT INTO tasks_management.t_tasks (id, c_title, c_description, c_is_done, c_is_done_time, user_id)
VALUES (1, 'New t 1', 'Hello!', false, current_date, 1);

INSERT INTO tasks_management.t_tasks (id, c_title, c_description, c_is_done, c_is_done_time, user_id)
VALUES (2, 'New t 2', 'Hello!', false, current_date, 2);

INSERT INTO tasks_management.t_tasks (id, c_title, c_description, c_is_done, c_is_done_time, user_id)
VALUES (3, 'New t 3', 'Hello!', false, current_date, 3);