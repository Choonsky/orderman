/*

Already done

----


create table if not exists persistent_logins (
                                               username varchar(100) not null,
                                               series varchar(64) primary key,
                                               token varchar(64) not null,
                                               last_used timestamp not null
);

delete from  user_role;
delete from  roles;
delete from  users;

INSERT INTO roles (id, name) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER'),
(3, 'ROLE_CHIEF'),
(4, 'ROLE_SUPPLIER');

INSERT INTO users (id, email, password, name) VALUES
(1, 'admin@gmail.com', '$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS', 'Администратор Admin'), -- password = "admin"
(2, 'user@gmail.com', '$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by', 'Пользователь User'); -- password = "user"
(3, 'chief@gmail.com', '$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by', 'Руководящий Chief'); -- password = "user"
(4, 'supplier@gmail.com', '$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by', 'Закупщик Supplier'); -- password = "user"

insert into user_role(user_id, role_id) values
(1,1),
(2,2),
(3,3),
(4,4);


*/