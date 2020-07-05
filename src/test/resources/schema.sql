create table authors (
                         id int auto_increment,
                         first_name varchar(30) not null,
                         last_name varchar(30) not null,
                         primary key (id)
);

create table publishers (
                            id int auto_increment,
                            name varchar(30) not null,
                            contact varchar(30) not null,
                            primary key (id)
);

create table books (
                       isbn varchar(13) not null,
                       title varchar(100) not null,
                       pub_date date,
                       cost int not null,
                       category enum(
                           'ACTION',
                           'CLASSIC',
                           'DETECTIVE',
                           'DRAMA',
                           'FANTASY',
                           'HISTORICAL',
                           'HORROR',
                           'HUMOR',
                           'MYTHOLOGY',
                           'ROMANCE',
                           'SCIENCE_FICTION',
                           'BIOGRAPHY',
                           'POETRY'
                           ) not null,
                       available_number int not null,
                       publisher_id int,
                       primary key (isbn),
                       foreign key (publisher_id) references publishers (id)
);

create table author_books (
                              author_id int not null,
                              book_ISBN varchar(13) not null,
                              foreign key (author_id) references authors(id),
                              foreign key (book_ISBN) references books(isbn),
                              unique (author_id, book_ISBN)
);

create table customers (
                           id int auto_increment,
                           first_name varchar(30) not null,
                           last_name varchar(30) not null,
                           phone_number varchar(13) not null,
                           email varchar(125),
                           payment_system enum(
                               'VISA',
                               'MASTERCARD',
                               'AMERICANEXPRESS',
                               'JBC',
                               'CHINAUNIONPAY'
                               ),
                           card_number varchar(16) not null,
                           card_holders_last_name varchar(30) not null,
                           card_holders_first_name varchar(30) not null,
                           card_valid_until date,
                           primary key (id)
);

create table customer_addresses (
                                    customer_id int not null,
                                    address_line1 varchar(100) not null ,
                                    address_line2 varchar(100),
                                    city varchar(20) not null ,
                                    country varchar(20) not null ,
                                    state varchar(2),
                                    zip_code  varchar(6) not null,
                                    foreign key (customer_id) references customers(id) on delete cascade
);

create table employees (
                           id int auto_increment,
                           first_name varchar(30),
                           last_name varchar(30),
                           email varchar(125),
                           phone_number varchar(13),
                           primary key (id)
);

create table orders (
                        id int auto_increment,
                        customer_id int not null,
                        address_line1 varchar(100) not null ,
                        address_line2 varchar(100),
                        city varchar(20) not null ,
                        country varchar(20) not null,
                        state varchar(2),
                        zip_code  varchar(6) not null,
                        order_date date not null,
                        pre_ordered boolean,
                        ship_date date,
                        primary key (id),
                        foreign key (customer_id) references customers(id) on delete cascade
);

create table order_items (
                             id int auto_increment,
                             is_paid boolean,
                             lease_end_date date,
                             lease_start_date date,
                             quantity_of_books int,
                             book_isbn varchar(13) not null,
                             order_id int,
                             primary key (id),
                             foreign key (book_isbn) references books(isbn) on delete cascade,
                             foreign key (order_id) references orders(id) on delete cascade
);

insert into authors (first_name, last_name)
    values
        ('Author name 1', 'Author lastname 1'),
        ('Author name 2', 'Author lastname 2'),
        ('Author name 3', 'Author lastname 3'),
        ('Author name 4', 'Author lastname 4'),
        ('Author name 5', 'Author lastname 5');

insert into publishers (contact, name)
    values
        ('Test contact 1', 'Test name 1'),
        ('Test contact 2', 'Test name 2'),
        ('Test contact 3', 'Test name 3'),
        ('Test contact 4', 'Test name 4'),
        ('Test contact 5', 'Test name 5');

insert into books (isbn, available_number, category, cost, pub_date, title, publisher_id)
    values
        ('111111aaaaaaa', 3, 'ACTION', 100, '2020-08-18', 'Test title 1', 1),
        ('222222bbbbbbb', 10, 'DETECTIVE', 200, '2019-01-01', 'Test title 2', 2),
        ('333333ccccccc', 14, 'HUMOR', 120, '2018-09-20', 'Test title 3', 3),
        ('444444ddddddd', 9, 'MYTHOLOGY', 135, '2001-10-30', 'Test title 4', 4),
        ('555555eeeeeee', 6, 'DETECTIVE', 40, '2002-12-31', 'Test title 5', 5),
        ('666666fffffff', 74, 'HUMOR', 42, '2000-02-20', 'Test title 6', 2);

insert into author_books (author_id, book_isbn)
    values
        (1, '111111aaaaaaa'),
        (2, '222222bbbbbbb'),
        (3, '333333ccccccc'),
        (4, '444444ddddddd'),
        (5, '555555eeeeeee'),
        (5, '111111aaaaaaa'),
        (4, '222222bbbbbbb'),
        (1, '333333ccccccc'),
        (2, '444444ddddddd');

insert into customers
        (first_name, last_name, phone_number, email, payment_system, card_number, card_holders_last_name, card_holders_first_name, card_valid_until)
    values
        ('Test name 1', 'Test lastname 1', 11111111111, 'test1@mail.com', 'VISA', '1111111111111111', 'Holders lastname 1', 'Holders name 1', '2021-02-20'),
        ('Test name 2', 'Test lastname 2', 22222222222, 'test2@mail.com', 'MASTERCARD', '2222222222222222', 'Holders lastname 2', 'Holders name 2', '2021-01-20'),
        ('Test name 3', 'Test lastname 3', 33333333333, 'test3@mail.com', 'CHINAUNIONPAY', '3333333333333333', 'Holders lastname 3', 'Holders name 3', '2019-02-20'),
        ('Test name 4', 'Test lastname 4', 44444444444, 'test4@mail.com', 'MASTERCARD', '4444444444444444', 'Holders lastname 4', 'Holders name 4', '2022-02-20'),
        ('Test name 5', 'Test lastname 5', 55555555555, 'test5@mail.com', 'VISA', '5555555555555555', 'Holders lastname 5', 'Holders name 5', '2021-06-20');

insert into customer_addresses (customer_id, address_line1, address_line2, city, country, state, zip_code)
    values
        (1, 'Test address 1 line 1', 'Test address 1 line 2', 'Test city 1', 'Test country 1', 'TS', '111111'),
        (2, 'Test address 2 line 1', 'Test address 2 line 2', 'Test city 2', 'Test country 2', 'T2', '222222'),
        (3, 'Test address 3 line 1', 'Test address 3 line 2', 'Test city 3', 'Test country 3', 'T3', '333333'),
        (4, 'Test address 4 line 1', 'Test address 4 line 2', 'Test city 4', 'Test country 4', 'T4', '444444'),
        (5, 'Test address 5 line 1', 'Test address 5 line 2', 'Test city 5', 'Test country 5', 'T5', '555555'),
        (1, 'Test address 1 line 1', 'Test address 1 line 2', 'Test city 1', 'Test country 1', 'TS', '111111');

insert into employees (first_name, last_name, email, phone_number)
    values
        ('Test name 1', 'Test lastname 1', 'test1@mail.com', '11111111111'),
        ('Test name 2', 'Test lastname 2', 'test2@mail.com', '22222222222'),
        ('Test name 3', 'Test lastname 3', 'test3@mail.com', '33333333333'),
        ('Test name 4', 'Test lastname 4', 'test4@mail.com', '44444444444'),
        ('Test name 5', 'Test lastname 5', 'test5@mail.com', '55555555555');

insert into orders
        (customer_id, address_line1, address_line2, city, country, state, zip_code, order_date, pre_ordered, ship_date)
    values
        (1, 'Test address 1 line 1', 'Test address 1 line 2', 'Test city 1', 'Test country 1', 'TS', '111111', '2020-05-10', 0, '2020-07-10'),
        (2, 'Test address 2 line 1', 'Test address 2 line 2', 'Test city 2', 'Test country 2', 'T2', '222222', '2020-06-10', 1, '2020-07-05'),
        (3, 'Test address 3 line 1', 'Test address 3 line 2', 'Test city 3', 'Test country 3', 'T3', '333333', '2020-03-10', 1, '2020-08-10'),
        (4, 'Test address 4 line 1', 'Test address 4 line 2', 'Test city 4', 'Test country 4', 'T4', '444444', '2020-04-10', 1, '2020-09-11'),
        (5, 'Test address 5 line 1', 'Test address 5 line 2', 'Test city 5', 'Test country 5', 'T5', '555555', '2020-02-10', 0, '2020-09-20'),
        (1, 'Test address 1 line 1', 'Test address 1 line 2', 'Test city 1', 'Test country 1', 'TS', '111111', '2020-06-10', 1, '2020-07-25');

insert into order_items (is_paid, lease_end_date, lease_start_date, quantity_of_books, book_isbn, order_id)
    values
        (1, '2020-10-10', '2020-06-10', 1, '111111aaaaaaa', 1),
        (0, '2020-11-03', '2020-07-03', 1, '444444ddddddd', 2),
        (0, '2020-12-10', '2020-08-02', 2, '333333ccccccc', 3),
        (1, '2020-10-07', '2020-09-07', 2, '111111aaaaaaa', 4),
        (1, '2020-11-10', '2020-08-10', 3, '444444ddddddd', 5),
        (0, '2020-11-09', '2020-08-14', 2, '555555eeeeeee', 6),
        (0, '2020-08-10', '2020-06-18', 1, '333333ccccccc', 1),
        (1, '2020-07-17', '2020-06-05', 2, '555555eeeeeee', 2);