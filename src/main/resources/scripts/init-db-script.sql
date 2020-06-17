CREATE TABLE customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL
);
CREATE TABLE book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    author VARCHAR(30) NOT NULL,
    genre VARCHAR(20) NOT NULL,
    dateRealise DATE NOT NULL
);
CREATE TABLE customer_card (
    id INT AUTO_INCREMENT PRIMARY KEY,
    createdAt DATE NOT NULL,
    customer_id INT NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer (id)
);
CREATE TABLE customer_card_book_list (
    customer_card_id INT NOT NULL,
    book_list_id INT NOT NULL,
    FOREIGN KEY (customer_card_id) REFERENCES customer (id),
    FOREIGN KEY (book_list_id) REFERENCES book (id),
    UNIQUE (customer_card_id, book_list_id)
);