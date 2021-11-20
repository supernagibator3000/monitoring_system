create table if not exists groups_students(
    group_id bigint not null,
    student_id bigint not null
);

create table if not exists groups_subjects(
    group_id bigint not null,
    subject_id bigint not null
);

create table if not exists subjects_events(
    subject_id bigint not null,
    event_id bigint not null
);

create table if not exists students_subjects(
    student_id bigint not null,
    subject_id bigint not null
);

create table if not exists students_scores(
    student_id bigint not null,
    score_id bigint not null
);

create table if not exists students_attendance(
    student_id bigint not null,
    attendance_id bigint not null
);

create table if not exists teachers_subjects(
    teacher_id bigint not null,
    subject_id bigint not null
);

create table if not exists users_roles(
    user_id bigint not null,
    role_id bigint not null
);

create table if not exists lessons_attendance(
    lesson_id bigint not null,
    attendance_id bigint not null
);

create table if not exists checkpoints_scores(
    checkpoint_id bigint not null,
    score_id bigint not null
);