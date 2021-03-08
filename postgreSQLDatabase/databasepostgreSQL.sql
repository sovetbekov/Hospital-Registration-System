create table patient(
name VARCHAR(50),
surname VARCHAR(50),
gender VARCHAR(50),
disease VARCHAR(50),
DOCTOR varchar(300),
CITY varchar(30),
EMAIL varchar(30),
PASSWORD varchar(20),
CONFIRMPASSWORD varchar(20),
order_ID SERIAL NOT NULL,
	primary key (order_id)
);

create table appointment(
order_ID SERIAL NOT NULL,
date_appoint DATE DEFAULT CURRENT_DATE,
	foreign key (order_id) references patient(order_id)
)

insert into appointment values((1))