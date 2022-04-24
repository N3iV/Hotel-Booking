use hotelbookingdb;
CREATE TABLE role(
	id bigint NOT NULL PRIMARY KEY auto_increment,
    name VARCHAR(100) NOT NULL
);
CREATE TABLE user(
	id bigint NOT NULL PRIMARY KEY auto_increment,
    username VARCHAR(150) NULL,
    password VARCHAR(150) NOT NULL,
    firstname VARCHAR(150) NOT NULL,
    lastname VARCHAR(150) NOT NULL,
    gender bool NULL,
    phonenumber VARCHAR(100) NULL,
    email VARCHAR(200) NOT NULL,
    roleid bigint NOT NULL,
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL
);
CREATE TABLE hotelmanager(
	id bigint NOT NULL PRIMARY KEY auto_increment,
    user_id bigint NOT NULL, 
    province_id bigint not null,
    hotel_name VARCHAR(200) NOT NULL,
    hotel_desc TEXT NULL,
    hotel_address VARCHAR(200) NOT NULL,
    image TEXT NULL,
    roleid bigint NOT NULL,
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL
);
CREATE TABLE typeroom(
	id bigint NOT NULL PRIMARY KEY auto_increment,
    name VARCHAR(100) NOT NULL ,
    quantity bigint NOT NULL,
    price bigint NOT NULL,
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL
);
CREATE TABLE hotelroom(
	id bigint NOT NULL PRIMARY KEY auto_increment,
    hotel_id bigint NOT NULL,
    type_id bigint NOT NULL,
    description TEXT NULL,
    status int NOT NULL,
    image TEXT NULL,
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL
);
CREATE TABLE booking(
	id bigint NOT NULL PRIMARY KEY auto_increment,
    room_id bigint NOT NULL,
    user_id bigint NOT NULL,
    hotel_id bigint NOT NULL,
    checkin_date TIMESTAMP NOT NULL,
    checkout_date TIMESTAMP NOT NULL,
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL
);
CREATE TABLE receipt(
	id bigint NOT NULL PRIMARY KEY auto_increment,
    booking_id  bigint NOT NULL,
    user_id bigint NOT NULL,
    sumprice bigint NOT NULL,
    createddate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL
);
CREATE TABLE province (
    id bigint primary key not null auto_increment,
    name varchar(100)
);

ALTER TABLE user ADD CONSTRAINT fk_user_role FOREIGN KEY (roleid) REFERENCES role(id);


ALTER TABLE hotelmanager ADD CONSTRAINT fk_hotelmanager_role FOREIGN KEY (roleid) REFERENCES role(id);
ALTER TABLE hotelmanager ADD CONSTRAINT fk_hotelmanager_user FOREIGN KEY (user_id) REFERENCES user(id);
ALTER TABLE hotelmanager ADD CONSTRAINT fk_hotelmanager_province FOREIGN KEY (province_id) REFERENCES province(id);


ALTER TABLE hotelroom ADD CONSTRAINT fk_hotelroom_hotelmanager FOREIGN KEY (hotel_id) REFERENCES hotelmanager(id);
ALTER TABLE hotelroom ADD CONSTRAINT fk_hotelroom_typeroom FOREIGN KEY (type_id) REFERENCES typeroom(id);


ALTER TABLE booking ADD CONSTRAINT fk_booking_hotelroom FOREIGN KEY (room_id) REFERENCES hotelroom(id);
ALTER TABLE booking ADD CONSTRAINT fk_booking_user FOREIGN KEY (user_id) REFERENCES user(id);
ALTER TABLE booking ADD CONSTRAINT fk_booking_hotelmanager FOREIGN KEY (hotel_id) REFERENCES hotelmanager(id);



ALTER TABLE receipt ADD CONSTRAINT fk_receipt_booking FOREIGN KEY (booking_id) REFERENCES booking(id);
ALTER TABLE receipt ADD CONSTRAINT fk_receipt_user FOREIGN KEY (user_id) REFERENCES user(id);




