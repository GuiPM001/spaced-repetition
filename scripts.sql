CREATE DATABASE spaced_repetition CHARACTER SET utf8;

USE spaced_repetition;

CREATE TABLE cards (
	id INT AUTO_INCREMENT PRIMARY KEY,
	front VARCHAR(255),
	back VARCHAR(255),
	next_review DATE,
	next_qtd_days INT
);