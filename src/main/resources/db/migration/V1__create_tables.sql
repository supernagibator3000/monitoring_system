create table if not exists groups(
    id bigserial primary key not null,
    name text not null
);

create table if not exists roles(
    id bigserial primary key not null,
    name text not null
);

create table if not exists subjects(
    id bigserial primary key not null,
    name text not null
);

---------------------------- Personalities

create table if not exists personalities(
    id bigserial primary key not null,
    first_name text not null,
    second_name text not null,
    middle_name text not null,
    email text not null
);

create table if not exists students(
    id bigserial primary key not null,
    personality_id bigint not null,
    student_card_id text not null,
    group_id bigint not null
);

create table if not exists teachers(
    id bigserial primary key not null,
    personality_id bigint not null
);

create table if not exists users(
    id bigserial primary key not null,
    personality_id bigint not null,
    username text not null,
    password text not null
);
---------------------------- Events

create table if not exists events(
    id bigserial primary key not null,
    name text not null,
    subject_id bigint not null
);

create table if not exists lessons(
    id bigserial primary key not null,
    event_id bigint not null
);

create table if not exists checkpoints(
    id bigserial primary key not null,
    event_id bigint not null
);

---------------------------- Marks

create table if not exists attendance(
    id bigserial primary key not null,
    student_id bigint not null,
    mark text not null,
    lesson_id bigint not null
);

create table if not exists scores(
    id bigserial primary key not null,
    student_id bigint not null,
    mark text not null,
    checkpoint_id bigint not null
);
