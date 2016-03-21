CREATE SEQUENCE VENDOR_SEQ START 1000 INCREMENT 1;

CREATE SEQUENCE MENU_SEQ START 100 INCREMENT 1;

CREATE SEQUENCE TRANSACTION_SEQ START 1000 INCREMENT 1;

CREATE TABLE CF_USER (
USER_ID BIGINT PRIMARY KEY,
PASSWORD varchar(15) NOT NULL,
EMAIL varchar(50) NOT NULL,
PHONE BIGINT NOT NULL,
FIRST_NAME varchar(50) NOT NULL,
LAST_NAME varchar(50) NOT NULL,
USER_TYPE varchar(10) NOT NULL);

CREATE TABLE VENDOR (
VENDOR_ID BIGINT PRIMARY KEY,
VENDOR_NAME varchar(200) NOT NULL,
PASSWORD varchar(15) NOT NULL,
VENDOR_EMAIL varchar(50) NOT NULL,
VENDOR_PHONE BIGINT NOT NULL,
INCHARGE varchar(100),
VENDOR_DETAIL varchar(300));

CREATE TABLE MENU (
MENU_ID BIGINT PRIMARY KEY,
VENDOR_ID BIGINT NOT NULL,
MENU_NAME varchar(200) NOT NULL,
MENU_DESCRIPTION varchar(500),
PRICE BIGINT NOT NULL,
AVAILABILITY BIGINT,
QUANTITY BIGINT);

CREATE TABLE TRANSACTION (
TRANSACTION_ID BIGINT PRIMARY KEY,
USER_ID BIGINT NOT NULL,
DATE DATE NOT NULL,
VENDOR_ID BIGINT NOT NULL, 
DELIVERY VARCHAR(10) DEFAULT 'N');

CREATE TABLE ORDER_ITEMS (
TRANSACTION_ID BIGINT,
MENU_ID BIGINT,
QUANTITY BIGINT NOT NULL,
PRIMARY KEY (TRANSACTION_ID, MENU_ID));

--Insert into CF_USER table
INSERT INTO CF_USER VALUES(320516,'password','sridhar131989@gmail.com',9962731987,'Prasanna','Kumar','Vendor');

INSERT INTO CF_USER VALUES(317158,'password','sridhar131989@gmail.com',9962731987,'Sridhar','Munendramani','User');

INSERT INTO CF_USER VALUES(372623,'password','sridhar131989@gmail.com',9962731987,'Sakthivel','Subbaiyan','Admin');

INSERT INTO CF_USER VALUES(257676,'password','sridhar131989@gmail.com',9962731987,'Rajesh','B','User');

INSERT INTO CF_USER VALUES(169667,'password','sridhar131989@gmail.com',9962731987,'Santhia','Rajendran','User');

INSERT INTO CF_USER VALUES(348661,'password','sridhar131989@gmail.com',9962731987,'Malathi','S','User');

INSERT INTO CF_USER VALUES(506795,'password','sridhar131989@gmail.com',9962731987,'Kavitha','K','Admin');

--Insert into VENDOR table
INSERT INTO VENDOR VALUES (nextval('vendor_seq'),'Virudhunagar','password','vendor_email@gmail.com',
9962731987,'Virudhunagar Incharge','Enter Virudhunagar description');

INSERT INTO VENDOR VALUES (nextval('vendor_seq'),'FoodExo','password','vendor_email@gmail.com',
9962731987,'FoodExo Incharge','Enter FoodExo description');

INSERT INTO VENDOR VALUES (nextval('vendor_seq'),'ISS','password','vendor_email@gmail.com',
9962731987,'ISS Incharge','Enter ISS description');

INSERT INTO VENDOR VALUES (nextval('vendor_seq'),'Curry and crunch','password','vendor_email@gmail.com',
9962731987,'Curry and crunch Incharge','Enter Curry and crunch description');

INSERT INTO VENDOR VALUES (nextval('vendor_seq'),'KCS','password','vendor_email@gmail.com',
9962731987,'KCS Incharge','Enter KCS description');

--Insert into MENU table
INSERT INTO MENU VALUES (nextval('menu_seq'),1000,'Mutton Biriyani','Mutton Biriyani with egg',
90.00,30,150);

INSERT INTO MENU VALUES (nextval('menu_seq'),1002,'Chicken Biriyani','Chicken Biriyani with egg',
80.00,80,100);

INSERT INTO MENU VALUES (nextval('menu_seq'),1001,'Chappathi','Chappathi with Kuruma',
60.00,60,120);

INSERT INTO MENU VALUES (nextval('menu_seq'),1001,'Sambar Rice','Sambar Rice with chips',
80.00,30,80);

INSERT INTO MENU VALUES (nextval('menu_seq'),1002,'Veg Meals','Veg meals with chips',
60.00,80,80);

INSERT INTO MENU VALUES (nextval('menu_seq'),1000,'Andhra Meals','Andhra meals with chips',
80.00,30,60);

INSERT INTO MENU VALUES (nextval('menu_seq'),1003,'Mutton Biriyani','Mutton Biriyani with egg',
90.00,30,150);

INSERT INTO MENU VALUES (nextval('menu_seq'),1003,'Chicken Biriyani','Chicken Biriyani with egg',
80.00,80,100);

INSERT INTO MENU VALUES (nextval('menu_seq'),1004,'Chappathi','Chappathi with Kuruma',
60.00,60,120);

INSERT INTO MENU VALUES (nextval('menu_seq'),1004,'Sambar Rice','Sambar Rice with chips',
80.00,30,80);
	
INSERT INTO MENU VALUES (nextval('menu_seq'),1005,'Veg Meals','Veg meals with chips',
60.00,80,80);

INSERT INTO MENU VALUES (nextval('menu_seq'),1005,'Andhra Meals','Andhra meals with chips',
80.00,30,60);

--Insert into TRANSACTION table

INSERT INTO TRANSACTION VALUES (nextval('transaction_seq'),320516,now(),1000,'N');		

INSERT INTO TRANSACTION VALUES (nextval('transaction_seq'),317158,now(),1000,'N');		

INSERT INTO TRANSACTION VALUES (nextval('transaction_seq'),317158,now(),1001,'N');	

INSERT INTO TRANSACTION VALUES (nextval('transaction_seq'),257676,now(),1001,'N');	

INSERT INTO TRANSACTION VALUES (nextval('transaction_seq'),320516,now(),1002,'N');	

INSERT INTO TRANSACTION VALUES (nextval('transaction_seq'),257676,now(),1002,'N');	

INSERT INTO TRANSACTION VALUES (nextval('transaction_seq'),317158,now(),1003,'N');	

INSERT INTO TRANSACTION VALUES (nextval('transaction_seq'),317158,now(),1003,'N');	

INSERT INTO TRANSACTION VALUES (nextval('transaction_seq'),257676,now(),1004,'N');	

INSERT INTO TRANSACTION VALUES (nextval('transaction_seq'),257676,now(),1004,'N');	

INSERT INTO TRANSACTION VALUES (nextval('transaction_seq'),257676,now(),1005,'N');	

INSERT INTO TRANSACTION VALUES (nextval('transaction_seq'),320516,now(),1005,'N');	

--Insert into ORDER_ITEMS table
INSERT INTO ORDER_ITEMS VALUES (1000,100,1);

INSERT INTO ORDER_ITEMS VALUES (1001,105,2);

INSERT INTO ORDER_ITEMS VALUES (1002,102,1);

INSERT INTO ORDER_ITEMS VALUES (1003,103,1);

INSERT INTO ORDER_ITEMS VALUES (1004,101,1);

INSERT INTO ORDER_ITEMS VALUES (1005,104,1);

INSERT INTO ORDER_ITEMS VALUES (1006,106,1);

INSERT INTO ORDER_ITEMS VALUES (1007,107,1);

INSERT INTO ORDER_ITEMS VALUES (1008,108,1);

INSERT INTO ORDER_ITEMS VALUES (1009,109,1);

INSERT INTO ORDER_ITEMS VALUES (1010,110,1);

INSERT INTO ORDER_ITEMS VALUES (1011,111,1);