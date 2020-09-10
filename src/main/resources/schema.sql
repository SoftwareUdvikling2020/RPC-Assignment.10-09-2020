-- This file has fixed name and location
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS BankTransaction;

CREATE TABLE Customer (
  accnum INTEGER PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  amount DOUBLE
);

CREATE TABLE BankTransaction (
  transactionUUID VARCHAR(250) PRIMARY KEY,
  accnumFrom INTEGER,
  accnumTo INTEGER,
  transactionMessage VARCHAR(250),
  amount DOUBLE
);