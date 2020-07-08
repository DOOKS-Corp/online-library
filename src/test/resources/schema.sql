drop table if exists
    authors,
    publishers,
    books,
    author_books,
    customers,
    customer_addresses,
    employees,
    orders,
    order_items;

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