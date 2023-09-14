--liquibase formatted sql

--changeset 1-base-schema:SavelyDomnikov

CREATE TABLE USERS
(
    id        BIGSERIAL,
    username  VARCHAR(25) NOT NULL,
    full_name VARCHAR(50) NOT NULL,
    password  TEXT        NOT NULL,
    role      VARCHAR(25) NOT NULL,
    email     VARCHAR(50) NOT NULL
);

ALTER TABLE USERS
    ADD CONSTRAINT users_db_pk PRIMARY KEY (id);

CREATE UNIQUE INDEX users_db_unique_username ON USERS (username);
CREATE UNIQUE INDEX users_db_unique_email ON USERS (email);

CREATE TABLE HOTEL
(
    id         BIGSERIAL,
    hotel_name VARCHAR(50) NOT NULL,
    stars      SMALLINT    NOT NULL,
    city       VARCHAR(50) NOT NULL
);

ALTER TABLE HOTEL
    ADD CONSTRAINT hotel_db_pk PRIMARY KEY (id),
    ADD CONSTRAINT hotel_db_check_stars CHECK ( stars >= 1 AND stars <= 5);

CREATE UNIQUE INDEX hotel_db_unique_name_and_city ON HOTEL (hotel_name, city);

CREATE TABLE ROOM
(
    id          BIGSERIAL,
    hotel_id    BIGINT   NOT NULL,
    room_number SMALLINT NOT NULL,
    room_floor  SMALLINT
);

ALTER TABLE ROOM
    ADD CONSTRAINT room_db_pk PRIMARY KEY (id),
    ADD CONSTRAINT room_db_fk_on_hotels FOREIGN KEY (hotel_id) REFERENCES HOTEL (id);

CREATE UNIQUE INDEX room_db_unique_hotel_and_room ON ROOM (hotel_id, room_number);


CREATE TABLE BOOKING
(
    id        BIGSERIAL,
    room_id   BIGINT NOT NULL,
    user_id   BIGINT NOT NULL,
    date_from DATE   NOT NULL,
    date_to   DATE   NOT NULL
);

ALTER TABLE BOOKING
    ADD CONSTRAINT booking_db_pk PRIMARY KEY (id),
    ADD CONSTRAINT booking_db_fk_on_rooms FOREIGN KEY (room_id) REFERENCES ROOM (id),
    ADD CONSTRAINT booking_db_fk_on_users FOREIGN KEY (user_id) REFERENCES USERS (id),
    ADD CONSTRAINT booking_db_check_dates CHECK ( date_from >= current_date AND date_from <= date_to );
