--liquibase formatted sql

--changeset 1-generate-data:SavelyDomnikov

INSERT INTO hotel (hotel_name, stars, city)
VALUES ('hotel_1', 5, 'Moscow'),
       ('hotel_2', 4, 'Sochi'),
       ('hotel_3', 4, 'Anapa'),
       ('hotel_4', 5, 'Saint Petersburg'),
       ('hotel_5', 3, 'Rostov');


INSERT INTO room (hotel_id, room_number, room_floor)
VALUES (1, 1, 5),
       (1, 2, 7),
       (2, 1, 1),
       (2, 2, 3),
       (3, 1, 4),
       (3, 2, 10),
       (4, 1, 11),
       (4, 2, 5),
       (5, 1, 1),
       (5, 2, 2);

INSERT INTO users (username, password, role)
VALUES ('user@mail.ru', '$2a$10$5ILUab1I2R3676m70s1HuuXrfqGHRcFQIB3I0Etk1hNuw/WlX.xwG', 'USER'),
       ('manager@mail.ru', '$2a$10$5ILUab1I2R3676m70s1HuuXrfqGHRcFQIB3I0Etk1hNuw/WlX.xwG', 'MANAGER');

INSERT INTO booking (room_id, user_id, date_from, date_to)
VALUES (1, 1, '2023.10.01', '2023.10.05'),
       (2, 1, '2023.10.10', '2023.10.11'),
       (3, 1, '2023.09.20', '2023.09.22'),
       (4, 1, '2023.11.01', '2023.11.07'),
       (5, 1, '2023.12.01', '2023.12.11')
