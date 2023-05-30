create database bookrental;
use bookrental;

create table user(
userID char(6) primary key,
username char(15) unique not null,
firstName varchar(50) not null,
lastName varchar(50) not null,
phoneNum varchar(50) not null unique,
email varchar(60) not null,
password varchar(255) not null,
image varchar(255),
gender varchar(1),
birthdate date,
rentedBooks int
);
create table favorite(
userID char(6),
bookID char(6),
dateAdded timestamp default current_timestamp,
foreign key(userID) references user (userID) on delete cascade on update cascade,
foreign key(bookID) references book (bookID) on delete cascade on update cascade
);
create table book(
bookID char(6) primary key unique,
bookName varchar(50) not null,
authorFirstName varchar(50) not null,
authorLastName varchar(50) not null,
publicationDate date not null,
inventory int not null,
rate float not null,
isbn varchar(17) not null unique,
price decimal(7,2) not null,
bookImage varchar(255)
);
create table mainGenre(
mainGenreID char(3) primary key,
mainGenreName varchar(50) not null,
mainGenreCover varchar(255)
);
create table genre(
genreID char(3) primary key,
genreName varchar(50) not null,
mainGenreID char(3) , foreign key (mainGenreID) references mainGenre(mainGenreID) on delete set null on update cascade,
genreCover varchar(255)
);
create table bookgenre(
  bookID char(6),
  genreID char(3),
  primary key (bookID, genreID),
  foreign key (bookID) references book(bookID),
  foreign key (genreID) references genre(genreID)
);
create table userCart(
userID char(6),
bookID char(6),
quantity int default 1,
amount decimal(8,2) not null,
rentedDays int default 1 not null,
primary key (userID,bookID),
foreign key(userID) references user (userID) on delete cascade on update cascade,
foreign key(bookID) references book (bookID) on delete cascade on update cascade
);
create table checkout(
checkoutID char(6) primary key,
userID char(6),
bookID char(6),
transactionID char(6),
addressID int,
quantity int default 1,
amount decimal(8,2) not null,
deliveryFee decimal(5,2),
libraryFee decimal(5,2) not null,
rentedDays int default 1 not null,
deliveryDateandTime timestamp,
pickupDateandTime timestamp,
checkoutDate timestamp default current_timestamp,
checkoutStatus varchar(50) not null,
foreign key(addressID) references deliveryAddress(addressID),
foreign key (transactionID) references transaction(transactionID),
foreign key(userID) references user (userID),
foreign key(bookID) references book (bookID)
);
create table deliveryAddress(
addressID int auto_increment primary key,
userID char(6),
name varchar(50) not null,
address varchar(255) not null,
landmark varchar(255),
notes varchar(255),
foreign key(userID) references user(userID)
);
create table rental(
rentalID char(6) primary key,
userID char(6),
bookID char(6),
checkoutID char(6),
startDate timestamp not null,
endDate timestamp not null,
returnDate timestamp,
returnCondition varchar(50),
rentStatus varchar(20)  not null,
foreign key(checkoutID) references checkout(checkoutID),
foreign key(userID) references user(userID),
foreign key(bookID) references book (bookID)
);
create table penalty(
penaltyID int auto_increment primary key,
userID char(6),
rentalID char(6),
transactionID char(6),
penaltyType varchar(100) not null,
penaltyAmount decimal(7,2) not null,
penaltyDate timestamp default current_timestamp,
paymentStatus varchar(100) not null,
foreign key(userID) references user(userID)
);
create table transaction(
transactionID char(6) primary key,
userID char(6),
paymentMethod varchar(50) not null,
amountToPay decimal(8,2) not null, 
amountPaid decimal(8,2) not null,
transactionDate timestamp default current_timestamp,
transactionStatus varchar(12) not null,
foreign key(userID) references user(userID)
);
create table admin (
	adminID INT AUTO_INCREMENT PRIMARY KEY,
    username varchar(50) not null unique,
    firstName varchar(50) not null,
	lastName varchar(50) not null,
    password varchar(255) not null,
    createdAt timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    lastLoginAt TIMESTAMP NULL
);


insert into mainGenre values
('FIC','Fiction',"../img/images/book.png"),('NFC','Non-Fiction',"../img/images/book.png");

insert into genre values
('HOR', 'Horror','FIC','../img/header/HorrorHeader.png'),
('FAN', 'Fantasy','FIC','../img/header/fantasyheader.png'),
('ROM', 'Romance','FIC','../img/header/RomanceHeader.png'),
('HIS', 'History','NFC','../img/header/historyheader.png'),
('TRA', 'Travel','NFC','../img/header/travelheader.png'),
('SLF', 'Self-Help','NFC','../img/header/selfhelpheader.png');

insert into book(bookID,bookName,authorFirstName,authorLastName,publicationDate,inventory,rate,isbn,price,bookImage) values
(generateBookID(),'Holly','Stephen','King','2023-09-23',2,4,9781668016138,8.00,'../img/books/holly.png'),
(generateBookID(),'Dracula','Bram','Stoker','2015-03-27',3,4.5,9781435159570,16.00,'../img/books/dracula.png'),
(generateBookID(),'Frakenstein','Mary','Shelley','2015-03-20',3,5,9781435159624,10.00,'../img/books/frankenstein.png'),
(generateBookID(),'IT','Stephen','King','2016-01-05',0,5,9781501142970,23.00,'../img/books/it.png');

insert into book(bookID,bookName,authorFirstName,authorLastName,publicationDate,inventory,rate,isbn,price,bookImage) values
(generateBookID(),'The Coven','Harper','Woods','2023-03-16',1,4,9798218171681,15.00,'../img/books/coven.png'),
(generateBookID(),"Harry Potter and the Sorcerers Stone's Book 1",'J.K','Rowling','2015-12-08',3,5, 9781781100486,20.00,'../img/books/harrypotter.png'),
(generateBookID(),"The Serpent and the Wings of Night",'Carissa','Broadbent','2022-08-30',2,4.5,9781957779003,13.00,'../img/books/serpent.png'),
(generateBookID(),'Uprooted','Naomi','Novik','2015-03-01',0,4.5,9780804179058,10.00,'../img/books/uprooted.png');

INSERT INTO bookgenre (bookID,genreID) VALUES ('BK0001','HOR'),('BK0002','HOR'),('BK0003','HOR'),('BK0004','HOR'); 
INSERT INTO bookgenre (bookID,genreID) VALUES ('BK0005','FAN'),('BK0006','FAN'),('BK0007','FAN'),('BK0008','FAN');
insert into bookgenre values ('BK0004','FAN'); 


insert admin (username, firstName,lastName,password) values ('admin','Cedric','Oporto','240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9');

DELIMITER $$
CREATE FUNCTION generateBookID() RETURNS char(6)

DETERMINISTIC
BEGIN
  DECLARE newID char(6);
  DECLARE bookCount int;
  set bookCount = (Select count(*) from book);
  SET newID = CONCAT('BK',LPAD(bookCount + 1,4,0));
  RETURN newID;
END $$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION generateUserID() RETURNS char(6)
DETERMINISTIC
BEGIN
  DECLARE newID char(6);
  DECLARE userCount int;
  set userCount = (Select count(*) from user);
  SET newID = CONCAT('UN',LPAD(userCount + 1,4,0));
  RETURN newID;
END $$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION generateCheckoutID() RETURNS char(6)
DETERMINISTIC
BEGIN
  DECLARE newID char(6);
  DECLARE checkoutCount int;
  set checkoutCount = (Select count(*) from checkout);
  SET newID = CONCAT('CN',LPAD(checkoutCount + 1,4,0));
  RETURN newID;
END $$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION generateTransactionID() RETURNS char(6)
DETERMINISTIC
BEGIN
  DECLARE newID char(6);
  DECLARE transactionCount int;
  set transactionCount = (Select count(*) from transaction);
  SET newID = CONCAT('TN',LPAD(transactionCount + 1,4,0));
  RETURN newID;
END $$
DELIMITER ;

DELIMITER $$
CREATE FUNCTION generateRentalID() RETURNS char(6)
DETERMINISTIC
BEGIN
  DECLARE newID char(6);
  DECLARE rentalCount int;
  set rentalCount = (Select count(*) from rental);
  SET newID = CONCAT('RN',LPAD(rentalCount + 1,4,0));
  RETURN newID;
END $$
DELIMITER ;
drop function generateBookID;