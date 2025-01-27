create schema if not exists users_management;

create schema if not exists tasks_management;

create table if not exists users_management.t_users
(
    id         bigserial primary key,
    c_username varchar not null check ( length(trim(c_username)) > 0 ) unique,
    c_email    varchar not null check ( length(trim(c_email)) > 0 ) unique,
    c_password varchar not null check ( length(trim(c_password)) > 6)
);

create table if not exists users_management.t_authorities
(
    id     bigserial primary key,
    c_authority_type varchar not null check ( length(trim(c_authority_type)) > 0 ) unique
);

create table if not exists users_management.user_authorities
(
    id           bigserial primary key,
    user_id      bigserial not null references users_management.t_users (id),
    authority_id bigserial not null references users_management.t_authorities (id),
    constraint uk_user_authority_pair unique (user_id, authority_id)
);

create table if not exists tasks_management.t_tasks
(
    id             bigserial primary key,
    c_title        varchar(50) not null check ( length(trim(c_title)) > 0 ),
    c_description  varchar(5000),
    c_is_done      bool        not null,
    c_is_done_time timestamp,
    user_id        bigserial         not null references users_management.t_users (id) on delete cascade
);