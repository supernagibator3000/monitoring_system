insert into personalities (id, first_name, second_name, middle_name, email) values (0, 'Админ', 'Админ', 'Админ', 'javatemplates03@gmail.com');
insert into users (id, personality_id, username, password) values (0, 0, 'admin', 'admin');

insert into users_roles (user_id, role_id) values (0, 1);
insert into users_roles (user_id, role_id) values (0, 2);