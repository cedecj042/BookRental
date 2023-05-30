use bookrental;

update book set inventory = 10;
select * from checkout order by checkoutID asc;
SELECT MAX(checkoutID) FROM checkout;
select * from genre;
select * from admin;
select distinct rentStatus from rental;
select startDate from rental where YEAR(startDate) = '2023' AND MONTH(startDate) = '01';
SELECT * FROM rental WHERE 1=1 and rentStatus = 'Checked Out';
select * from checkout;
select * from book natural join bookgenre where  bookID ='BK0010';
select * from book where  bookID ='BK0010';
select * from user natural join book;
select * from transaction;
select * from bookgenre;
select * from genre;
select * from deliveryAddress;
insert into bookgenre values('BK0009','ROM');
update user set image ='F:\\USJR\\Second year - 2nd Sem\\OOP\\Projects\\Book Rental Project\\book rental\\src\\img\\images\\userIcon.png' where userID = 'UN0001';
delete from bookgenre where bookID ='BK0010';
delete from book where bookID ='BK0010';
delete from user where userID = 'UN0002';

update book set bookImage = 'F:\\USJR\\Second year - 2nd Sem\\OOP\\Projects\\Book Rental Project\\book rental\\books\\holly.png' where  bookID = 'BK0001';
update book set bookImage = 'F:\\USJR\\Second year - 2nd Sem\\OOP\\Projects\\Book Rental Project\\book rental\\books\\dracula.png' where  bookID = 'BK0002';
update book set bookImage = 'F:\\USJR\\Second year - 2nd Sem\\OOP\\Projects\\Book Rental Project\\book rental\\books\\frankenstein.png' where  bookID = 'BK0003';
update book set bookImage = 'F:\\USJR\\Second year - 2nd Sem\\OOP\\Projects\\Book Rental Project\\book rental\\books\\it.png' where  bookID = 'BK0004';
update book set bookImage = 'F:\\USJR\\Second year - 2nd Sem\\OOP\\Projects\\Book Rental Project\\book rental\\books\\coven.png' where  bookID = 'BK0005';
update book set bookImage = 'F:\\USJR\\Second year - 2nd Sem\\OOP\\Projects\\Book Rental Project\\book rental\\books\\harrypotter.png' where  bookID = 'BK0006';
update book set bookImage = 'F:\\USJR\\Second year - 2nd Sem\\OOP\\Projects\\Book Rental Project\\book rental\\books\\serpent.png' where  bookID = 'BK0007';
update book set bookImage = 'F:\\USJR\\Second year - 2nd Sem\\OOP\\Projects\\Book Rental Project\\book rental\\books\\uprooted.png' where  bookID = 'BK0008';
update book set bookImage = 'F:\\USJR\\Second year - 2nd Sem\\OOP\\Projects\\Book Rental Project\\book rental\\books\\itendswithus.png' where  bookID = 'BK0009';


update genre set genreCover = 'F:\\USJR\\Second year - 2nd Sem\\OOP\\Projects\\Book Rental Project\\book rental\\header\\HorrorHeader.png' where genreID = 'HOR' ;
update genre set genreCover = 'F:\\USJR\\Second year - 2nd Sem\\OOP\\Projects\\Book Rental Project\\book rental\\header\\fantasyheader.png' where genreID = 'FAN' ;
update genre set genreCover = 'F:\\USJR\\Second year - 2nd Sem\\OOP\\Projects\\Book Rental Project\\book rental\\header\\RomanceHeader.png' where genreID = 'ROM' ;
update genre set genreCover = 'F:\\USJR\\Second year - 2nd Sem\\OOP\\Projects\\Book Rental Project\\book rental\\header\\historyheader.png' where genreID = 'HIS' ;
update genre set genreCover = 'F:\\USJR\\Second year - 2nd Sem\\OOP\\Projects\\Book Rental Project\\book rental\\header\\travelheader.png' where genreID = 'TRA' ;
update genre set genreCover = 'F:\\USJR\\Second year - 2nd Sem\\OOP\\Projects\\Book Rental Project\\book rental\\header\\selfhelpheader.png' where genreID = 'SLF' ;