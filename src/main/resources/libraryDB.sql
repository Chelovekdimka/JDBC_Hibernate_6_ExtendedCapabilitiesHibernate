CREATE DATABASE IF NOT EXISTS library;

USE library;

DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS book;

CREATE TABLE author
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(45),
    last_name VARCHAR(45)
);
CREATE TABLE book
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(45)
);


CREATE TABLE authorship (
  author_id INT,
  book_id INT,
  PRIMARY KEY (author_id, book_id),
  FOREIGN KEY (author_id) REFERENCES author (id),
  FOREIGN KEY (book_id) REFERENCES book (id)
);